import Cat from "./Cat";

const CatList = props => {
    return(
     <table>
         <thead>
            <tr>
                <th>Name</th>
                <th>Breed</th>
                <th>Age</th>
                <th>Adopted</th>
            </tr>
         </thead>
         <tbody>
            {props.cats.map(cat => <Cat key={cat.id} cat={cat} handleAdopt={props.handleAdopt} handleDelete={props.handleDelete} handleUpdate={props.handleUpdate}/>)}
         </tbody>
     </table>
    )
}

export default CatList;