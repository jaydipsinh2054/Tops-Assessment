function StatCard({ title, value, icon, color }) {
    return (
        <div className="card stat-card h-100">
            <div className="card-body d-flex justify-content-between align-items-center">

                <div>
                    <p className="text-muted mb-2">{title}</p>

                    <h2 className="fw-bold mb-0">
                        {value}
                    </h2>
                </div>

                <div
                    className="stat-icon"
                    style={{ backgroundColor: color }}
                >
                    <i className={`bi ${icon}`}></i>
                </div>

            </div>
        </div>
    );
}

export default StatCard;