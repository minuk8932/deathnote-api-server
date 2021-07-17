package com.rest.api.controller.report;


import com.rest.api.dto.request.report.ReportRequestDto;
import com.rest.api.dto.response.BaseResponseDto;
import com.rest.api.dto.response.ErrorResponseDto;
import com.rest.api.dto.response.report.ReportListResponseDto;
import com.rest.api.dto.response.report.ReportResponseDto;
import com.rest.api.service.report.ReportService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"3. Report"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/report")
public class ReportController {


    private final ReportService reportService;

    @ApiOperation(value = "report", notes = "report 생성")
    @ApiResponses({
            @ApiResponse(code = 200, message = "report 생성 성공"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PostMapping
    public ResponseEntity<BaseResponseDto> createReport(@RequestBody ReportRequestDto reportRequestDto) {
        ReportResponseDto reportResponseDto = reportService.createReport(reportRequestDto);
        return new ResponseEntity<>(new BaseResponseDto(HttpStatus.CREATED.value(), "데이터 생성 성공", reportResponseDto.getReportId()), HttpStatus.CREATED);
    }


    @ApiOperation(value = "report", notes = "report 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "report 조회 성공"),
            @ApiResponse(code = 404, message = "존재하지 않는 report 접근"),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponseDto.class),
    })
    @GetMapping
    public ResponseEntity<ReportListResponseDto> getReportByAccountId(@ApiParam(value = "소환사 ID", required = true) @RequestParam String accountId) {
        List<ReportResponseDto> reportResponseDtos = reportService.getReportResponsesWithAccountId(accountId);
        return new ResponseEntity<>(new ReportListResponseDto(HttpStatus.OK.value(), "데이터 조회 성공", reportResponseDtos), HttpStatus.OK);
    }

    @ApiOperation(value = "report", notes = "report 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "report 수정 성공"),
            @ApiResponse(code = 404, message = "존재하지 않는 report 접근"),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponseDto.class),
    })
    @PutMapping(path = "/{id}")
    public ResponseEntity<ReportListResponseDto> updateReportByReportId(@ApiParam(value = "소환사 이름", required = true) @PathVariable Long id) {
        List<ReportResponseDto> reportResponseDtos = null;
        // TODO: Report update by Id. (check reachable report contents)

        return new ResponseEntity<>(new ReportListResponseDto(HttpStatus.OK.value(), "데이터 수정 성공", reportResponseDtos), HttpStatus.OK);
    }

    @ApiOperation(value = "report", notes = "report 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "report 삭제 성공"),
            @ApiResponse(code = 404, message = "존재하지 않는 report 접근"),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponseDto.class),
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ReportListResponseDto> deleteReportByReportId(@ApiParam(value = "소환사 이름", required = true) @PathVariable Long id) {
        List<ReportResponseDto> reportResponseDtos = null;
        // TODO: Report delete by Id. (check reachable report contents)

        return new ResponseEntity<>(new ReportListResponseDto(HttpStatus.OK.value(), "데이터 삭제 성공", reportResponseDtos), HttpStatus.OK);
    }


}
