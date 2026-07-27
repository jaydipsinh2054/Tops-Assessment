import api from "./api";

export const getWallet = async () => {

    const response = await api.get("/api/wallet");

    return response.data;

};

export const addMoney = async (amount) => {

    const response = await api.post(
        "/api/wallet/add-money",
        { amount }
    );

    return response.data;

};

export const getTransactions = async () => {

    const response = await api.get(
        "/api/wallet/transactions"
    );

    return response.data;

};