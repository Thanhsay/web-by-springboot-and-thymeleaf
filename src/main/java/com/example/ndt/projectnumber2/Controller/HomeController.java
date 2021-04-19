package com.example.ndt.projectnumber2.Controller;

import com.example.ndt.projectnumber2.Model.Brand;
import com.example.ndt.projectnumber2.Model.Modell;
import com.example.ndt.projectnumber2.Model.Series;
import com.example.ndt.projectnumber2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home(Model model, @RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "9") int offset){
        //model.addAttribute("ModelFindAll", userService.modelFindAll() );
        Page<Modell> modellPage = (Page<Modell>)
                userService.modelFindAll(PageRequest.of(page - 1, offset));
        model.addAttribute("ModelFindAll", modellPage);

        /*
        Long totalModel = userService.modelTotal();
        //Double totalPage = Math.ceil(totalModel/offset);
        int totalPage;
        if(totalModel/offset==0){
            totalPage = (int) (totalModel/offset);
        }else {
            totalPage = (int) (totalModel/offset + 1);
        }
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("offset", offset);
        model.addAttribute("currentPage", page);
                 */
        model.addAttribute("brandFindAll", userService.brandFindAll());

        return "index";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam Long id){
        Modell modell = new Modell();
        modell = userService.modelFindById(id);
        Series series = new Series();
        series = userService.seriesFindById(modell.getModel_series_id().getSeries_id());
        Brand brand = new Brand();
        brand = userService.brandFindById(series.getSeries_brand_id().getBrand_id());
        model.addAttribute("ModelFindById", modell);
        model.addAttribute("seriesFindById", series);
        model.addAttribute("brandFindById", brand);
        model.addAttribute("brandFindAll", userService.brandFindAll());
        return "Frontend/detailProduct";
    }

    @GetMapping("/brand")
    public String brandProduct(Model model, @RequestParam Long id, @RequestParam String name){
        Brand brand = new Brand();
        brand = userService.brandFindById(id);
        model.addAttribute("brandFindById", brand);
        /*
        List<Series> listSeries = new ArrayList<>();
        listSeries = userService.seriesFindByBrand(id);
        model.addAttribute("listSeries", listSeries);
        List<Modell> listModell = new ArrayList<>();
        for (Object c : listSeries){
            Series series = new Series();
            series = (Series) c;
            listModell = userService.modellFindBySeries(series.getSeries_id());
        }
        model.addAttribute("listModel", listModell);
         */
        List<Series> seriesList = new ArrayList<>();
        List<Modell> modellList = new ArrayList<>();
        seriesList = userService.seriesFindByBrand(id);
        for (Object o : seriesList){
            Series series = new Series();
            series = (Series) o;
            List<Modell> modellList1 = new ArrayList<>();
            modellList1 = userService.modellFindBySeries(series.getSeries_id());
            for (Object m : modellList1){
                Modell modell1 = new Modell();
                modell1 = (Modell) m;
                modellList.add(modell1);
            }
        }
        model.addAttribute("ModelList", modellList);
        return "Frontend/brandProduct";
    }

    @GetMapping("/smartphones")
    public String allSmartPhone(Model model, @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "9") int offset){
        Page<Modell> modellPage = userService.modelFindAndSort(page, offset);
        model.addAttribute("ModelFindAll", modellPage);
        Long totalModel = userService.modelTotal();
        Long totalPages;
        if(totalModel%offset==0){
            totalPages = totalModel/offset;
        }else
            totalPages = totalModel/offset +1;
        model.addAttribute("currentPage", page);
        model.addAttribute("offset", offset);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("brandFindAll", userService.brandFindAll());
        return "Frontend/smartPhone";
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "name") String name, Model model){
        List<Modell> modellList = userService.modelFindByString(name);
        model.addAttribute("ModelFindAll", modellList);
        model.addAttribute("name", name);
        return "Frontend/search";
    }
}
