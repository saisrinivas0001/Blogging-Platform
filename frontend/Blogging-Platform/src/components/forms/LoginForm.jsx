import React, { useState } from 'react';
import './LoginForm.css'

function LoginForm() {
    const [loginData, setLoginData] = useState({
        username: '',
        password: ''
    });
    const [error, setError] = useState({});

    const handleChange = (e) => {
        setLoginData({ ...loginData, [e.target.name]: e.target.value });
    };

    const validateForm = () => {
        const formError = {};
        let isValid = true;

        if (!loginData.username.trim()) {
            isValid = false;
            formError.username = 'Username is required!';
        }

        if (!loginData.password.trim()) {
            isValid = false;
            formError.password = 'Password is required!';
        }

        if (isValid) {
            return true;
        } else {
            setError(formError);
            return false;
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const validForm = validateForm();
        if (validForm) {
            alert(`Welcome, ${loginData.username}!`);
            setLoginData({ username: '', password: '' });
            setError({});
        }
    };

    return (
        <div className="login-form">
            <div className="login-container">
                <form id="login-form" onSubmit={handleSubmit}>
                    <div className="form-elements">
                        <h2 id="login-title">Login Form</h2>
                    </div>
                    <div className="form-elements">
                        <label htmlFor="username">Username: </label>
                        <input
                            type="text"
                            id="username"
                            name="username"
                            value={loginData.username}
                            onChange={handleChange}
                            className={error.username ? 'error-input' : ''}
                        />
                        <div id="username-error">{error && <span>{error.username}</span>}</div>
                    </div>
                    <div className="form-elements">
                        <label htmlFor="password">Password: </label>
                        <input
                            type="password"
                            id="password"
                            name="password"
                            value={loginData.password}
                            onChange={handleChange}
                            className={error.password ? 'error-input' : ''}
                        />
                        <div id="password-error">{error && <span>{error.password}</span>}</div>
                    </div>
                    <br />
                    <div className="form-elements">
                        <button id="submit-button" type="submit">
                            Login
                        </button>
                    </div>
                    <br />
                </form>
            </div>
        </div>
    );
}

export default LoginForm;