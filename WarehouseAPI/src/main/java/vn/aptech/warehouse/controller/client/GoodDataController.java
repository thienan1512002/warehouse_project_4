/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import java.io.IOException;
import vn.aptech.warehouse.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.aptech.warehouse.entity.GoodData;
import vn.aptech.warehouse.service.GoodDataService;

/**
 *
 * @author Jack
 */
@Controller
@RequestMapping("/goods")
public class GoodDataController {
    @Autowired
    private GoodDataService service;
    
    @GetMapping(value="")
    public String index(Model model){
        model.addAttribute("goods", service.findAll());
        return "goods/index2";
    }
    @GetMapping(value="/create")
    public String create(Model model){
        GoodData good = new GoodData();
        model.addAttribute("good", good);
        return "goods/create2";
    }
    
    @PostMapping(value="/save")
    public String save(Model model, @ModelAttribute(name="good") GoodData good,
            BindingResult bindingResult,
            @RequestParam("image") MultipartFile multipartFile,
            RedirectAttributes ra) throws IOException{
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        good.setImage(fileName);
         
        GoodData savedGood = service.save(good);
 
        String uploadDir = "goods-photos/" + savedGood.getGoods_no();
 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        ra.addFlashAttribute("message", "The goods has been save successfully!");
        return "redirect:/goods";
    }
    //luu kho thay doi hinh
    @PostMapping(value="/save2")
    public String save2(Model model, @ModelAttribute(name="good") GoodData good,
            BindingResult bindingResult,
            @RequestParam(name="image", required = false) MultipartFile multipartFile,
            @RequestParam String image2,
            RedirectAttributes ra) throws IOException{
        if(multipartFile.isEmpty()){
            good.setImage(image2);
            service.save(good);
            ra.addFlashAttribute("message", "The goods has been updated successfully!");
            return "redirect:/goods";
        }else{
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        good.setImage(fileName);
         
        GoodData savedGood = service.save(good);
 
        String uploadDir = "goods-photos/" + savedGood.getGoods_no();
 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        ra.addFlashAttribute("message", "The goods has been updated successfully!");
        return "redirect:/goods";
        }
        
    }
    @GetMapping(value="/update/{no}")
    public ModelAndView update(@PathVariable("no")String no){
        ModelAndView mav = new ModelAndView("goods/update");
        GoodData good = service.findByNo(no);
        mav.addObject("good", good);
        return mav;
    }
    
}
