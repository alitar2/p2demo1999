import axios from "axios";
import React, { SyntheticEvent, useState } from "react";
import { Button, Container, FormControl } from "react-bootstrap"
import { useNavigate } from "react-router-dom"

interface User{
    username:String,
    password:String,
    teamID:number
}


export const Register:React.FC = () =>{
    

    const [newUser,setNewUser] = useState<User>({username:"",password:"",teamID:0});
    const navigate = useNavigate();


    //once again, specifying data type for event to be type-safe. NOT NEEDED!
    const storeValues = (event:React.ChangeEvent<HTMLInputElement>) =>{

        const name = event.target.name;
        const value = event.target.value;

        // understand this syntax this way: in setNewUser mutator, any callback function passed in it 
        // will receive the latest state value in it automatically.
        // the callback/arrow function takes the whole user object as a parameter,
        //  unpacks it using spread operator, 
        // and then returns the WHOLE object again, EXCEPT the value to be changed, which is appended into the object as needed
        setNewUser((newUser)=>({...newUser, [name]:value}));
    }

    const register = async () =>{
        const response = await axios.post("http://localhost:8080/users",newUser)
        .then(()=>alert("User "+newUser.username+" created!"))
        .catch((error)=>{alert("Registration failed! Make sure all fields are correct."+error)})


    }

    return (
      <>
        <Container className="d-flex align-items-center flex-column mt-5">
          <h3>Register</h3>
          <div>
            <FormControl
              type="text"
              placeholder="Username"
              name="username"
              onChange={storeValues}
            />
          </div>
          <div>
            <FormControl
              type="password"
              placeholder="Password"
              name="password"
              onChange={storeValues}
            />
          </div>
          <div>
            <FormControl
              type="number"
              placeholder="Team ID"
              name="teamID"
              onChange={storeValues}
            />
          </div>
          <div>
            <Button onClick={register}>Register!</Button>
            <Button onClick={()=>{navigate("/")}}>Go Back</Button>
          </div>
        </Container>
      </>
    );
}