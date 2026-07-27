function AuthLayout({ title, children }) {
    return (
        <div className="container-fluid vh-100 bg-light">
            <div className="row h-100">

                <div className="col-lg-6 d-none d-lg-flex bg-primary text-white justify-content-center align-items-center">
                    <div className="text-center">
                        <h1 className="display-4 fw-bold">🚲 Glide</h1>
                        <p className="lead">
                            Smart Bike Rental System
                        </p>
                    </div>
                </div>

                <div className="col-lg-6 d-flex justify-content-center align-items-center">
                    <div style={{ width: "400px" }}>
                        <h2 className="mb-4 text-center">{title}</h2>

                        {children}

                    </div>
                </div>

            </div>
        </div>
    );
}

export default AuthLayout;