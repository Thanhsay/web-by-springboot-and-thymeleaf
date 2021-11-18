package com.example.ndt.projectnumber2.Controller;

import com.example.ndt.projectnumber2.Model.Modell;
import com.example.ndt.projectnumber2.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class AdminController implements IAdmin {
    @Value("${UPLOAD_FOLDER}")
    public String UPLOAD_FOLDER;
    @Autowired
    AdminService adminService;
    @GetMapping("/admin")
    public String adminPage(){
        return "index1";
    }

    @Override
    @GetMapping("/admin/add")
    public String add(Model model){
        Modell modell = new Modell();
        model.addAttribute("modelAdd", modell);
        return "Frontend/Admin/add";
    }

    @PostMapping("/admin/do-add")
    public String doAdd(RedirectAttributes flaRedirectAttributes, Modell modell,
                        @RequestParam(name = "file") MultipartFile file){
        Modell modell1 = (Modell) modell;
        try{
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate.getMonthValue();
            int year = localDate.getYear();
            String savedFolder = UPLOAD_FOLDER+month+"_"+year;

            File dir = new File(savedFolder);
            if(!dir.exists()||dir.isFile()){
                dir.mkdir();
            }
            String fileName = System.currentTimeMillis()+file.getOriginalFilename();
            byte[] getBytes = file.getBytes();
            Path path = Paths.get(dir.getAbsolutePath()+"/"+fileName);
            Files.write(path, getBytes);
            modell.setModel_image(savedFolder.replace(UPLOAD_FOLDER, "")+fileName);
            adminService.addModel(modell);
            flaRedirectAttributes.addAttribute("message", "Add successfully");
        }catch (Exception e){
            e.printStackTrace();
            flaRedirectAttributes.addAttribute("error", "Add failed"+e.getMessage());
        }
        /*
        if(adminService.addModel(modell)){
            flaRedirectAttributes.addFlashAttribute("success", "Add successfully!");
        }else
            flaRedirectAttributes.addAttribute("error", "add failed");
         */
        return "redirect: /admin/add";
    }

    @Override
    public String list() {
        int test = 3;
        return null;
    }

    @Override
    public String update() {
        int test = 2;
        return null;
    }

    @Override
    public String delete() {
        int test = 1;
        return null;
    }
}
