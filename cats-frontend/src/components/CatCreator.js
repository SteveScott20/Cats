
const CatCreator = props => {
    return(
        <form onSubmit={(e) => props.handleCreate(
            {
                name: e.target.name.value,
                breed: e.target.breed.value,
                age: e.target.age.value,
                adopted: false
            })}>
            <input type="text-box" placeholder="Enter new kitty name" name="name" />
            <input type="text-box" placeholder="Enter new kitty breed" name="breed" />
            <input type="text-box" placeholder="Enter new kitty age" name="age" />
            <input type="submit" />
        </form>
    )

}

export default CatCreator;