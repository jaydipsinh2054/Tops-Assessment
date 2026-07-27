import { useEffect, useState } from "react";
import { ToastContainer, toast } from "react-toastify";

import DashboardLayout from "../../layouts/DashboardLayout";

import {
    getWallet,
    addMoney,
    getTransactions
} from "../../services/walletService";

function Wallet() {

    const [wallet, setWallet] = useState(null);

    const [transactions, setTransactions] = useState([]);

    const [amount, setAmount] = useState("");

    const [loading, setLoading] = useState(true);

    const loadWallet = async () => {

        try {

            setLoading(true);

            const walletData = await getWallet();

            const transactionData = await getTransactions();

            setWallet(walletData);

            setTransactions(transactionData);

        } catch (error) {

            console.error(error);

            toast.error("Failed to load wallet.");

        } finally {

            setLoading(false);

        }

    };

    useEffect(() => {

        loadWallet();

    }, []);

    const handleAddMoney = async () => {

        if (!amount || Number(amount) <= 0) {

            toast.warning("Enter a valid amount.");

            return;

        }

        try {

            await addMoney(Number(amount));

            toast.success("Money added successfully!");

            setAmount("");

            loadWallet();

        } catch (error) {

            console.error(error);

            toast.error(
                error.response?.data?.message ||
                "Failed to add money."
            );

        }

    };

    const getBadgeClass = (type) => {

        return type === "CREDIT"
            ? "bg-success"
            : "bg-danger";

    };

    return (

        <DashboardLayout role="rider">

            <div className="mb-4">

                <h2 className="dashboard-title">

                    My Wallet

                </h2>

                <p className="text-muted">

                    Manage your wallet and view transaction history.

                </p>

            </div>

            {

                loading ?

                    (

                        <div className="text-center mt-5">

                            <div
                                className="spinner-border text-primary"
                                role="status"
                            ></div>

                        </div>

                    )

                    :

                    (

                        <>

                            <div className="card shadow-sm border-0 mb-4">

                                <div className="card-body text-center">

                                    <h5>

                                        Current Balance

                                    </h5>

                                    <h1 className="display-5 text-success fw-bold">

                                        ₹{wallet.balance}

                                    </h1>

                                    <p className="text-muted">

                                        {wallet.userName}

                                    </p>

                                </div>

                            </div>

                            <div className="card shadow-sm border-0 mb-4">

                                <div className="card-body">

                                    <h5 className="mb-3">

                                        Add Money

                                    </h5>

                                    <div className="row">

                                        <div className="col-md-8">

                                            <input
                                                type="number"
                                                className="form-control"
                                                placeholder="Enter amount"
                                                value={amount}
                                                onChange={(e) =>
                                                    setAmount(e.target.value)
                                                }
                                            />

                                        </div>

                                        <div className="col-md-4">

                                            <button
                                                className="btn btn-success w-100"
                                                onClick={handleAddMoney}
                                            >

                                                Add Money

                                            </button>

                                        </div>

                                    </div>

                                </div>

                            </div>

                            <div className="card shadow-sm border-0">

                                <div className="card-body">

                                    <h5 className="mb-3">

                                        Transaction History

                                    </h5>

                                    {

                                        transactions.length === 0 ?

                                            (

                                                <div className="alert alert-info">

                                                    No transactions found.

                                                </div>

                                            )

                                            :

                                            (

                                                <div className="table-responsive">

                                                    <table className="table table-hover align-middle">

                                                        <thead>

                                                            <tr>

                                                                <th>ID</th>

                                                                <th>Type</th>

                                                                <th>Amount</th>

                                                                <th>Date</th>

                                                            </tr>

                                                        </thead>

                                                        <tbody>

                                                            {

                                                                transactions.map((transaction) => (

                                                                    <tr key={transaction.id}>

                                                                        <td>

                                                                            {transaction.id}

                                                                        </td>

                                                                        <td>

                                                                            <span
                                                                                className={`badge ${getBadgeClass(transaction.type)}`}
                                                                            >

                                                                                {transaction.type}

                                                                            </span>

                                                                        </td>

                                                                        <td>

                                                                            ₹{transaction.amount}

                                                                        </td>

                                                                        <td>

                                                                            {

                                                                                new Date(
                                                                                    transaction.transactionDate
                                                                                ).toLocaleString()

                                                                            }

                                                                        </td>

                                                                    </tr>

                                                                ))

                                                            }

                                                        </tbody>

                                                    </table>

                                                </div>

                                            )

                                    }

                                </div>

                            </div>

                        </>

                    )

            }

            <ToastContainer position="top-right" />

        </DashboardLayout>

    );

}

export default Wallet;