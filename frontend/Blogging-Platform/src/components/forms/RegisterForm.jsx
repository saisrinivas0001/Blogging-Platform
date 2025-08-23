import React, { useState } from 'react'
import './RegisterForm.css'

function RegisterForm() {
    const [userData, setData] = useState({
                                    first_name : "",
                                    last_name : "",
                                    gender : "",
                                    dob : "",
                                    username : "",
                                    email : "",
                                    password : "",
                                    confirmPassword : "",
                                    isCheck : false
                                })
    const [error, setError] = useState({})

    const handleChange = (e)=>{
        setData({...userData, [e.target.name] : e.target.value})
    }

    const validateForm = ()=>{
        const formError = {}
        let isValid = true

        if(!userData.first_name.trim() || userData.first_name === null){
            isValid = false
            formError.first_name = "This field is Required..!"
        }

        if(!userData.last_name.trim() || userData.last_name === null){
            isValid = false
            formError.last_name = "This field is Required..!"
        }

        if(!userData.gender.trim() || userData.gender === null){
            isValid = false
            formError.gender = "This field is Required..!"
        }

        if(!userData.dob.trim() || userData.dob === null){
            isValid = false
            formError.dob = "This field is Required..!"
        }

        const usernameRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{5,}$/


        if(!userData.username.trim() || userData.username === null){
            isValid = false
            formError.username = "Username is Required..!"
        }else if(!usernameRegex.test(userData.username)){
            isValid = false
            formError.username = "Username should atleast 5 character length, and contains uppercase, lowercase and numbers only..!"
        }

        const emailRegex = /^[^@\s]+@[^@\s]+\.[^@\s]+$/

        if(!userData.email.trim() || userData.email === null){
            isValid = false
            formError.email = "Email is Required..!"
        }else if(!emailRegex.test(userData.email)){
            isValid = false
            formError.email = "Please Enter a Valid Email Address..!"
        }

        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[\d])(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]{8,}$/

        if(!userData.password.trim() || userData.password === null){
            isValid = false
            formError.password = "Password is Required..!"
        }else if(!passwordRegex.test(userData.password)){
            isValid = false
            formError.password = "Password must contain atleast a lowercase, uppercase, number and a special character and it should be 8 character length..!"
        }

        if(userData.password !==userData.confirmPassword){
            isValid = false
            formError.confirmPassword = "Password Not Matched..!"
        }else if(!userData.confirmPassword.trim() || userData.confirmPassword === null){
            isValid = false
            formError.confirmPassword = "This field is required..!"
        }

        if(!userData.isCheck){
            isValid = false
            formError.isCheck = "This field is required..!"
        }


        if(isValid){
            return true
        }else{
            setError(formError)
        }
    }

    const handleSubmit = async (e)=>{
        e.preventDefault()

        const validForm = validateForm()
        if(validForm){
            alert(`${userData.first_name + " " + userData.last_name} is Registered Successfully..!`)
            setData({
                                    first_name : "",
                                    last_name : "",
                                    gender : "",
                                    dob : "",
                                    username : "",
                                    email : "",
                                    password : "",
                                    confirmPassword : "",
                                    isCheck : false
                                })
            setError({})
        }else{

        }
    }

  return (
    <div className='register-form'>
        <div className='register-container'>
            <form id='register-form' onSubmit={handleSubmit}>
                <div className='form-elements'>
                    <h2 id="register-title">Registration Form</h2>
                </div>
                <div className='form-elements'>
                    <label htmlFor="first_name">First Name: </label>
                    <input type="text" 
                        id='first_name'
                        name='first_name'
                        value={userData.first_name}
                        onChange={handleChange}
                        className={error.first_name ? "error-input" : ""}
                        />
                    <div id="first_name-error">{error && <span>{error.first_name}</span>}</div>
                </div>
                <div className='form-elements'>
                    <label htmlFor="last_name">Last Name: </label>
                    <input type="text" 
                        id='last_name'
                        name='last_name'
                        value={userData.last_name}
                        onChange={handleChange}
                        className={error.last_name ? "error-input" : ""}
                        />
                    <div id="last_name-error">{error && <span>{error.last_name}</span>}</div>
                </div>
                <div className='form-elements'>
                    <label htmlFor="gender">Gender
                        <label htmlFor="male">Male
                            <input type="radio"
                                    id='male'
                                    name='gender'
                                    value={"male"}
                                    onChange={handleChange}
                                    className={error.gender ? "error-input" : ""}
                            />
                        </label>
                        <label htmlFor="female">Female
                            <input type="radio"
                                    id='female'
                                    name='gender'
                                    value={"female"}
                                    onChange={handleChange}
                                    className={error.gender ? "error-input" : ""} />
                        </label>
                    </label>
                    <div id="gender-error">{error && <span>{error.gender}</span>}</div>
                </div>
                <div className='form-elements'>
                    <label htmlFor="dob">Date of Birth: </label>
                    <input type="date" 
                        id='dob'
                        name='dob'
                        value={userData.dob}
                        onChange={handleChange}
                        className={error.dob ? "error-input" : ""}
                        />
                    <div id="dob-error">{error && <span>{error.dob}</span>}</div>
                </div>
                <div className='form-elements'>
                    <label htmlFor="username">Username: </label>
                    <input type="text" 
                        id='username'
                        name='username'
                        value={userData.username}
                        onChange={handleChange}
                        className={error.username ? "error-input" : ""}
                        />
                    <div id="username-error">{error && <span>{error.username}</span>}</div>
                </div>
                <div className='form-elements'>
                    <label htmlFor="email">Email: </label>
                    <input type="email" 
                        id='email'
                        name='email'
                        value={userData.email}
                        onChange={handleChange}
                        className={error.email ? "error-input" : ""}
                        />
                    <div id="email-error">{error && <span>{error.email}</span>}</div>
                </div>
                <div className='form-elements'>
                    <label htmlFor="password">Password: </label>
                    <input type="password" 
                        id='password'
                        name='password'
                        value={userData.password}
                        onChange={handleChange}
                        className={error.password ? "error-input" : ""}
                        />
                    <div id="password-error">{error && <span>{error.password}</span>}</div>
                </div>
                <div className='form-elements'>
                    <label htmlFor="confirmPassword">Confirm Password: </label>
                    <input type="password" 
                        id='confirmPassword'
                        name='confirmPassword'
                        value={userData.confirmPassword}
                        onChange={handleChange}
                        className={error.confirmPassword ? "error-input" : ""}
                        />
                    <div id="confirmPassword-error">{error && <span>{error.confirmPassword}</span>}</div>
                </div>
                <div className=''>
                    <input type="checkbox"
                            id="isCheck"
                            name="isCheck"
                            checked={userData.isCheck}
                            onChange={(e) => setData({ ...userData, isCheck: e.target.checked })}
                            className={error.isCheck ? "error-input" : ""}
                            />
                    <label htmlFor="isCheck">I agree with all Terms and Conditions..!</label>
                    <div id="isCheck-error">{error && <span>{error.isCheck}</span>}</div>
                </div> <br />
                <div className='form-elements'>
                    <button id='submit-button' type='submit'>Submit</button>
                </div> <br />
            </form>
        </div>
    </div>
  )
}

export default RegisterForm