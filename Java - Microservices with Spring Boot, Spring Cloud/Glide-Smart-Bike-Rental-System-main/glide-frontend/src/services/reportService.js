import api from "./api";

export const getStationReports = async () => {

    const response = await api.get("/api/reports/stations");

    return response.data;

};