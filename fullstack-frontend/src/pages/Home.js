import React, {useEffect, useState} from 'react'
import axios from "axios";
import { Link, useParams } from 'react-router-dom';

export default function Home(){
    const [users, setUsers] = useState([]);

    // eslint-disable-next-line no-unused-vars
    const {id} = useParams()

    useEffect(()=>{
        loadUsers();
        // console.log("Full Stack running");
    }, []);

    const loadUsers = async()=>{
        const result = await axios.get("http://localhost:8080/fullstack2/users");
            // console.log(result);
            // console.log(result.data);
        setUsers(result.data)
    }


    const deleteUser = async(id)=>{
        await axios.delete(`http://localhost:8080/fullstack2/user/${id}`)
        loadUsers();
    }
  return (
    <div className='container'>
        <div className='py-4'>

        <table className="table border shadow">
            <thead>
                <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">UserName</th>
                <th scope="col">EMail</th>
                <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                {/* <td>ID</td>
                <td>Name</td>
                <td>User</td>
                <td>Email</td> */}

                {
                    users.map((user, index)=>(
                        <tr>
                            <td key={index}>{index+1}</td>
                            <td>{user.name}</td>
                            <td>{user.username}</td>
                            <td>{user.email}</td>



                            <td>
                                <Link className='btn btn-primary mx-2' to={`/viewuser/${user.id}`}>
                                    View
                                </Link>

                                <Link className='btn btn-outline-primary max-2'
                                to={`/edituser/${user.id}`}
                                >
                                    Edit
                                </Link>

                                <button className='btn btn-danger mx-2'
                                onClick={()=>deleteUser(user.id)}>
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))
                }


            </tbody>
        </table>




        </div>
    </div>
  )
}
