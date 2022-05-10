import React, { useState, useEffect } from "react";
import StudentService from "../services/StudentService";

const Student = props => {
    const initialStudentState = {
      id: null,
      name: "",
      serie: "",
      email: ""
    };
    const [currentStudent, setCurrentStudent] = useState(initialStudentState);
    const [message, setMessage] = useState("");
  
    const getStudent = id => {
      StudentService.get(id)
        .then(response => {
          setCurrentStudent(response.data);
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    };
  
    useEffect(() => {
      getStudent(props.match.params.id);
    }, [props.match.params.id]);
  
    const handleInputChange = event => {
      const { name, value } = event.target;
      setCurrentStudent({ ...currentStudent, [name]: value });
    };

    const updateStudent = () => {
      StudentService.update(currentStudent.id, currentStudent)
        .then(response => {
          console.log(response.data);
          setMessage("The Student was updated successfully!");
        })
        .catch(e => {
          console.log(e);
        });
    };
  
    const deleteStudent = () => {
      StudentService.remove(currentStudent.id)
        .then(response => {
          console.log(response.data);
          props.history.push("/alunos");
        })
        .catch(e => {
          console.log(e);
        });
    };

    const createStudent = () => {
      StudentService.create(currentStudent)
        .then(response => {
          console.log(response.data);
          props.history.push("/alunos");
        })
        .catch(e => {
          console.log(e);
        });
    };
    

    return (
      <div>
        {currentStudent ? (
          <div className="container">
          <div className="mb-3">
            <label for="title">Nome</label>
            <input type="text" className="form-control" id="name" name="name" value={currentStudent.name} onChange={handleInputChange} />
          </div>
          <div className="mb-3">
            <label for="title">Série</label>
            <input type="text" className="form-control" id="serie" name="serie" value={currentStudent.serie} onChange={handleInputChange} />
          </div>
          <div className="mb-3">
            <label for="title">Email</label>
            <input type="text" className="form-control" id="email" name="email" value={currentStudent.email} onChange={handleInputChange} />
          </div>
          <div className="d-grid gap-2 d-md-block">
            <button type="submit" className="btn btn-success" onClick={updateStudent}>Atualizar</button>
            <button className="btn btn-danger" onClick={deleteStudent}>Excluir</button>
          </div>
          <p>{message}</p>
        </div>
        ) : (
          <div>
            <br />
            <p>Please click on a Student...</p>
          </div>
        )}
      </div>
    );
  };
  
  export default Student;