import axios from "axios";
import { useEffect, useState } from "react"
import { Button, Container, Table } from "react-bootstrap"
import { useNavigate } from "react-router-dom";

interface Team{
    teamID:number,
    teamName:string,
    teamLocation:string
}


interface User{
    userID:number,
    username:string,
    role:string,
    team:Team
}

export const Users:React.FC = () =>{

    const [users,setUsers] = useState<User[]>([]);

    const navigate = useNavigate();

    useEffect(()=>{
        getAllUsers();
    },[])

    const getAllUsers = async() =>{

        const userData = await axios
          .get("http://localhost:8080/users", { withCredentials: true })
          .then((response) => {
            return response.data;
          })
          .then((data) => setUsers(data));

    }

    return(
        <Container>
            <h1>Users</h1>
            <Table className="table-primary table-hover">
                <thead className="table-dark">
                    <tr>
                        <th>User ID</th>
                        <th>Username</th>
                        <th>Team Name</th>
                        <th>Role</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody>
                    {users.map((user:User)=>(
                        <tr>
                            <td>{user.userID}</td>
                            <td>{user.username}</td>
                            <td>{user.team.teamName}</td>
                            <td>{user.role}</td>
                            <td>
                                {user.role==="Player" ? <Button>Promote</Button> : <Button>Demote</Button>}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>
            <Button onClick={()=>{navigate("/")}}>Go Back</Button>
        </Container>
    )
}