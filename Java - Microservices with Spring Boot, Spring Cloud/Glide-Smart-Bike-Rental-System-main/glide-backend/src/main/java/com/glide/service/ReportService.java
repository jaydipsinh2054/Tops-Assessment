package com.glide.service;

import com.glide.dto.StationReportResponse;

import java.util.List;

public interface ReportService {

    List<StationReportResponse> getStationReports();

}