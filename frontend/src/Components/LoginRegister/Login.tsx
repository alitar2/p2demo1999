import axios from "axios";
import { useState } from "react";
import { Button, Container, FormControl } from "react-bootstrap";
import { useNavigate } from "react-router-dom"


interface User {
  username: String,
  password: String
}



export const Login:React.FC = () =>{


    const [user, setUser] = useState<User>({username:"",password:""})

    //useNavigate hook helps us change URL programatically
    const navigate = useNavigate();

    const storeValues = (event:React.ChangeEvent<HTMLInputElement>) =>{
        const name = event.target.name;
        const value = event.target.value;

        setUser((user)=>({...user,[name]:value}));

    }

    const login = async() =>{
        
        // need to add withCredentials:true to EVERY single Axios HTTP request to ensure proper usage of HTTP session on backend.
        const response = await axios.post("http://localhost:8080/auth",user,{withCredentials:true})
        .then((data)=>{
          if (data.data.role === "Player"){
            navigate("/teams");
          }
          else{
            navigate("/users")
          }
        })
    }

    return (
      <>
        <Container className="d-flex align-items-center flex-column mt-5 gap-3">
          <h3>Login</h3>
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
          <br></br>
          <div className="d-flex gap-1">
            <Button onClick={login}>Login</Button>
            <Button onClick={() => navigate("/teams")}>View Teams</Button>
            <Button onClick={() => navigate("/register")}>
              Register new user
            </Button>
          </div>
        </Container>
      </>
    );
}