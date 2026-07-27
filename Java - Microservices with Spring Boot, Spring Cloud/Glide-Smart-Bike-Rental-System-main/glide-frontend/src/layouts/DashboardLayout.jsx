import Navbar from "../components/layout/Navbar";
import OperatorSidebar from "../components/layout/OperatorSidebar";
import RiderSidebar from "../components/layout/RiderSidebar";

function DashboardLayout({ children, role = "operator" }) {

    return (

        <div className="d-flex">

            {role === "operator" ? <OperatorSidebar /> : <RiderSidebar />}

            <div
                className="flex-grow-1"
                style={{
                    minHeight: "100vh",
                    background: "#f4f7fb"
                }}
            >

                <Navbar />

                <div className="container-fluid p-4">

                    {children}

                </div>

            </div>

        </div>

    );

}

export default DashboardLayout;