import api from "./api";

export const startRide = async (reservationId) => {

    const response = await api.post(
        `/api/rides/start/${reservationId}`
    );

    return response.data;
};

export const getMyRides = async () => {

    const response = await api.get("/api/rides/my");

    return response.data;
};

export const endRide = async (rideId, stationId) => {

    const response = await api.post(
        `/api/rides/end/${rideId}?stationId=${stationId}`
    );

    return response.data;
};