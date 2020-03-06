package com.example.springbootormbeetlsqldemo.controller;

import com.example.springbootormbeetlsqldemo.pojo.User;
import com.example.springbootormbeetlsqldemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


@RestController
@Api(tags = "用户")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("saveUser")
    @ResponseBody
    @ApiOperation("新增用户")
    public String save() {
        User user = new User();
        user.setAge(11);
        user.setName("zhangsan");
        userService.saveUser(user);
        return "SUCCESS";
    }

    @GetMapping("deleteUser")
    @ResponseBody
    @ApiOperation("删除用户")
    public String delete() {
        userService.deleteUser(1L);
        return "SUCCESS";
    }

    @GetMapping("updateUser")
    @ResponseBody
    @ApiOperation("修改用户")
    public String update() {
        User user = new User();
        user.setId(1);
        user.setAge(11);
        user.setName("lisi");
        User saveUser = userService.updateUser(user);
        return "SUCCESS";
    }

    @GetMapping("selectUser")
    @ResponseBody
    @ApiOperation("查询单个用户")
    public String select() {
        User saveUser = userService.getUser(1L);
        return "SUCCESS";
    }

    @GetMapping("selectByPage")
    @ResponseBody
    @ApiOperation("分页查询用户")
    public String selectByPage() {
        userService.getUserByPage(1, 10);
        return "SUCCESS";
    }

    @GetMapping("export2Excel")
    @ResponseBody
    @ApiOperation("导出用户信息至Excel")
    public String export2Excel(HttpServletResponse response) throws IOException {
        SXSSFWorkbook workbook = null;
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //创建工作簿
            workbook = new SXSSFWorkbook();
            // 打开压缩功能 防止占用过多磁盘
            workbook.setCompressTempFiles(true);
            // 创建一个工作表
            Sheet sheet = workbook.createSheet();
            List<User> users = fakeList();
            for (int i = 0; i < users.size(); i++) {
                // 创建一行
                Row titleRow = sheet.createRow(i);
                User user = users.get(i);
                Cell cell0 = titleRow.createCell(0);
                cell0.setCellValue(user.getId());
                Cell cell1 = titleRow.createCell(1);
                cell1.setCellValue(user.getName());
            }
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=user_info.xlsx");
            // 将工作簿写入输出流
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                //使用完毕后将产生的临时文件删除
                workbook.dispose();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
        return "SUCCESS";
    }

    private List<User> fakeList() {
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setId(100);
        user1.setName("hello");
        list.add(user1);
        User user2 = new User();
        user2.setId(101);
        user2.setName("world");
        list.add(user2);
        return list;
    }

}
