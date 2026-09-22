package tw.com.ispan.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.ProductBean;
import tw.com.ispan.dto.CustomResponse;
import tw.com.ispan.service.ProductService;

@RestController
@RequestMapping("/ajax/pages/products")
public class ProductAjaxController {
    private ProductService productService;
    public ProductAjaxController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/find")
    public CustomResponse find(@RequestBody String json) {
        long count = productService.count(json);
        List<ProductBean> products = productService.find(json);
        if(products!=null) {
            return new CustomResponse(null, null, count, products);
        } else {
            return new CustomResponse(null, null, count, new ArrayList<>());
        }
    }

    @GetMapping("/{id}")
    public CustomResponse findById(@PathVariable Integer id) {
        ProductBean product = productService.findById(id);
        if(product!=null) {
            return new CustomResponse(null, null, null, List.of(product));
        } else {
            return new CustomResponse(null, null, null, new ArrayList<>());
        }
    }

    @PostMapping
    public CustomResponse create(@RequestBody String json) {
        JSONObject obj = new JSONObject(json);
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
        if(id==null) {
            return new CustomResponse(false, "id是必要欄位", null, null);
        } else if(productService.exists(id)) {
            return new CustomResponse(false, "id已存在", null, null);
        } else {
            ProductBean insert = productService.create(json);
            if(insert == null) {
                return new CustomResponse(false, "新增失敗", null, null);
            } else {
                return new CustomResponse(true, "新增成功", null, null);
            }
        }
    }

    @PutMapping("/{id}")
    public CustomResponse modify(@PathVariable Integer id, @RequestBody String json) {
        JSONObject obj = new JSONObject(json);
		Integer temp = obj.isNull("id") ? null : obj.getInt("id");
        if(id==null || temp==null) {
            return new CustomResponse(false, "id是必要欄位", null, null);
        } else if(!productService.exists(temp)) {
            return new CustomResponse(false, "id不存在", null, null);
        } else {
            ProductBean update = productService.modify(json);
            if(update == null) {
                return new CustomResponse(false, "修改失敗", null, null);
            } else {
                return new CustomResponse(true, "修改成功", null, null);
            }
        }
    }

    @DeleteMapping("/{id}")
    public CustomResponse remove(@PathVariable Integer id) {
        if(id==null) {
            return new CustomResponse(false, "id是必要欄位", null, null);
        } else if(!productService.exists(id)) {
            return new CustomResponse(false, "id不存在", null, null);
        } else {
            if(!productService.remove(id)) {
                return new CustomResponse(false, "刪除失敗", null, null);
            } else {
                return new CustomResponse(true, "刪除成功", null, null);
            }
        }
    }
}
