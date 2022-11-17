package com.ssafy.hw.model.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.hw.model.dao.ProductDao;
import com.ssafy.hw.model.dto.Product;
import com.ssafy.hw.model.dto.SearchCondition;
import com.ssafy.hw.util.PageNavigation;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao dao;


    @Override
    @Transactional
    public int insertProduct(Product product) {
        MultipartFile file = product.getFile();
        int result = 0;

        if (file != null) {
            String saveFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String uploadPath = "C:\\temp";

            File target = new File(uploadPath, saveFileName);

            if (!new File(uploadPath).exists()) {
                new File(uploadPath).mkdirs();
            }
            try {
                product.setFileName(saveFileName);
                product.setFileUri(target.getCanonicalPath());
                result = dao.insertProduct(product);
                FileCopyUtils.copy(file.getBytes(), target);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return result;
        }
        else {
            try {
                dao.insertProduct(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 20;
        }
    }

    @Override
    @Transactional
    public int updateProduct(Product product) {
        return dao.updateProduct(product);
    }

    @Override
    @Transactional
    public int deleteProduct(String pCode) {
        return dao.deleteProduct(pCode);
    }

    @Override
    @Transactional
    public Product selectProductBypCode(String pCode) {
        return dao.selectProductBypCode(pCode);
    }

    @Override
    @Transactional
    public Map<String, Object> selectProductBySearchConditionWithPaging(SearchCondition con) {
        int totalCount = dao.getTotalCount(con);
        PageNavigation nav = new PageNavigation(con.getCurrentPage(), totalCount);

        Map<String, Object> pagingResult = new HashMap<>();

        pagingResult.put("products", dao.selectByCondition(con));
        pagingResult.put("navigation", nav);

        return pagingResult;
    }


}
