import {useState} from "react";

const Cat = props => {

    const [editing, setEditing] = useState(false);
    const [newName, setNewName] = useState(props.cat.name);
    const [newBreed, setNewBreed] = useState(props.cat.breed);
    const [newAge, setNewAge] = useState(props.cat.age);

    return(
        <tr>
            <td>{(editing
                ?<input type="text-box" name="name" placeholder="New name" onChange={(e) => setNewName(e.target.value)}/>
                :<p>{props.cat.name}</p>)}</td>
            <td>{(editing
                ?<input type="text-box" name="breed" placeholder="New breed" onChange={(e) => setNewBreed(e.target.value)}/>
                :<p>{props.cat.breed}</p>)}</td>
            <td>{(editing
                ?<input type="text-box" name="age" placeholder="New age" onChange={(e) => setNewAge(e.target.value)}/>
                :<p>{props.cat.age}</p>)}</td>
            <td>{props.cat.adopted.toString()}</td>
            <td><button onClick={() => props.handleAdopt(props.cat)}>Adopt</button></td>
            <td><button onClick={() => props.handleDelete(props.cat.id)}>Delete</button></td>
            <td>{(editing
                ?<button onClick={() => {
                    setEditing(!editing);
                    props.handleUpdate({name: newName, breed: newBreed, age: newAge, adopted: props.cat.adopted, id: props.cat.id});
                }}>Submit</button>
                :<button onClick={() => {
                setEditing(!editing);
            }}>Update</button>)}
                </td>
        </tr>
    )
}

export default Cat;