import axios from "axios";
import { useEffect, useState } from "react"
import { Button, Container, Table } from "react-bootstrap"
import { useNavigate } from "react-router-dom";

interface Team{
    teamID:number,
    teamName:string,
    teamLocation:string
}


export const Teams:React.FC = () =>{

const [teams, setTeams] = useState<Team[]>([]);


//function for GET request to get teams from backend. Seperating it from useEffect hook is good practice.

const getAllTeams = async () => {

    const response = await axios.get("http://localhost:8080/teams",{withCredentials:true});

    setTeams(response.data);
}

const deleteTeam = (teamID:number,event:any) =>{
    alert ("After being triggered by " + typeof(event) + ", Team "+teamID+" has been deleted (but not really hehe)")
}

//this useEffect will load once, on component load
useEffect(() => {
    getAllTeams();
},[]);

const navigate = useNavigate();

    return (
      <Container>
        <Button onClick={() => {navigate("/");}}>Go Back</Button>
        <h3>Teams</h3>
        <Table>
          <thead>
            <tr>
              <th>Team ID</th>
              <th>Team Name</th>
              <th>Location</th>
            </tr>
          </thead>
          <tbody>
            {teams.map((team: Team) => {
              return (
                <tr>
                  <td>{team.teamID}</td>
                  <td>{team.teamName}</td>
                  <td>{team.teamLocation}</td>
                  {/* for any event listener functions, MAKE SURE TO PUT IT IN AN ARROW FUNCTION! Otherwise it will run indefinitely. */}
                  <Button
                    className="btn-danger"
                    onClick={(event) => deleteTeam(team.teamID, event)}
                  >
                    {" "}
                    Options
                  </Button>
                </tr>
              );
            })}
          </tbody>
        </Table>
      </Container>
    );
}