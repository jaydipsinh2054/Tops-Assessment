import api from "./api";

// ----------------------
// Register
// ----------------------

export const registerUser = async (registerData) => {

    const response = await api.post(
        "/api/auth/register",
        registerData
    );

    return response.data;

};

// ----------------------
// Login
// ----------------------

export const loginUser = async (loginData) => {

    const response = await api.post(
        "/api/auth/login",
        loginData
    );

    return response.data;

};