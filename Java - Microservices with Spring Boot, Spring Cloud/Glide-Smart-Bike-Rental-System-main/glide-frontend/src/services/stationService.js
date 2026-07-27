import api from "./api";

// Get all stations
export const getAllStations = async () => {

    const response = await api.get("/api/stations");

    return response.data;

};

// Add station
export const addStation = async (stationData) => {

    const response = await api.post(
        "/api/stations",
        stationData
    );

    return response.data;

};

// Update station
export const updateStation = async (id, stationData) => {

    const response = await api.put(
        `/api/stations/${id}`,
        stationData
    );

    return response.data;

};

// Delete station
export const deleteStation = async (id) => {

    const response = await api.delete(
        `/api/stations/${id}`
    );

    return response.data;

};