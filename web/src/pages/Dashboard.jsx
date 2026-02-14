import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/axios';

const Dashboard = () => {
    const [user, setUser] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const response = await api.get('/user/me');
                setUser(response.data);
            } catch (err) {
                console.error(err);
                if (err.response && err.response.status === 401) {
                    handleLogout();
                }
            }
        };

        fetchUser();
    }, []);

    const handleLogout = () => {
        localStorage.removeItem('user');
        navigate('/login');
    };

    if (!user) return <p>Loading...</p>;

    return (
        <div className="card">
            <h1>Welcome, {user.username}!</h1>
            <p><strong>Email:</strong> {user.email}</p>
            <p><strong>User ID:</strong> {user.id}</p>
            <div style={{ marginTop: '20px' }}>
                <button onClick={handleLogout} style={{ backgroundColor: '#ff4d4d', color: 'white' }}>
                    Logout
                </button>
            </div>
        </div>
    );
};

export default Dashboard;
