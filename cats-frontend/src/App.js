import Shelter from "./components/Shelter";
import {useEffect, useState} from "react";
import axios from "axios";

function App() {

  const [loading, setLoading] = useState(true);
  const [cats, setCats] = useState([]);

  useEffect(() => {
    getCats();
  }, []);

  async function getCats(){
    setLoading(true);
    await axios.get("http://localhost:8080/cats")
        .then(value => setCats(value.data));
    setLoading(false);
  }

  async function handleCreate(cat){
    await axios.post("http://localhost:8080/cats", cat)
    getCats();
  }

  async function handleAdopt(cat){
    await axios.patch("http://localhost:8080/cats/"+cat.id, {adopted: !cat.adopted})
    getCats();
  }

  async function handleDelete(id){
    await axios.delete("http://localhost:8080/cats/"+id);
    getCats();
  }

  async function handleUpdate(cat){
    await axios.patch("http://localhost:8080/cats/"+cat.id, cat);
    getCats();
  }

  return (
      <div>
        {(!loading && <Shelter
            cats={cats}
            handleCreate={handleCreate}
            handleAdopt={handleAdopt}
            handleDelete={handleDelete}
            handleUpdate={handleUpdate}
        />)}
      </div>
  );
}

export default App;
