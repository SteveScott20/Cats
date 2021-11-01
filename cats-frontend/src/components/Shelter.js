import CatCreator from "./CatCreator";
import CatList from "./CatList";

const Shelter = props => {
    return (
        <div>
            <CatCreator handleCreate={props.handleCreate}/>
            <CatList cats={props.cats} handleAdopt={props.handleAdopt} handleDelete={props.handleDelete} handleUpdate={props.handleUpdate}/>
        </div>
    )
}

export default Shelter;