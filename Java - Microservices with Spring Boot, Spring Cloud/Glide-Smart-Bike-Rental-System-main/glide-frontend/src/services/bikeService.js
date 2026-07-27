import api from "./api";

// Get all bikes
export const getAllBikes = async () => {

    const response = await api.get("/api/bikes");

    return response.data;

};

// Get bike by ID
export const getBikeById = async (id) => {

    const response = await api.get(
        `/api/bikes/${id}`
    );

    return response.data;

};

// Add bike
export const addBike = async (bikeData) => {

    const response = await api.post(
        "/api/bikes",
        bikeData
    );

    return response.data;

};

// Update bike
export const updateBike = async (id, bikeData) => {

    const response = await api.put(
        `/api/bikes/${id}`,
        bikeData
    );

    return response.data;

};

// Delete bike
export const deleteBike = async (id) => {

    const response = await api.delete(
        `/api/bikes/${id}`
    );

    return response.data;

};

// Get bikes by station
export const getBikesByStation = async (stationId) => {

    const response = await api.get(
        `/api/bikes/station/${stationId}`
    );

    return response.data;

};