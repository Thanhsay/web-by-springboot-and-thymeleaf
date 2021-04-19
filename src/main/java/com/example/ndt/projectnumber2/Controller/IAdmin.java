package com.example.ndt.projectnumber2.Controller;

import org.springframework.ui.Model;

public interface IAdmin {
    public String add(Model model);
    public String list();
    public String update();
    public String delete();
}
