package com.github.ethanpang.controller;

import com.github.ethanpang.core.Result;
import com.github.ethanpang.core.ResultGenerator;
import com.github.ethanpang.model.DailyReport;
import com.github.ethanpang.service.DailyReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/08/07.
*/
@RestController
@RequestMapping("/daily/report")
public class DailyReportController {
    @Resource
    private DailyReportService dailyReportService;

    @PostMapping
    public Result add(@RequestBody DailyReport dailyReport) {
        dailyReportService.save(dailyReport);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        dailyReportService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody DailyReport dailyReport) {
        dailyReportService.update(dailyReport);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        DailyReport dailyReport = dailyReportService.findById(id);
        return ResultGenerator.genSuccessResult(dailyReport);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DailyReport> list = dailyReportService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
