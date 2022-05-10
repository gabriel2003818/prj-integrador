import React, { useState } from "react";
import StudentService from "../services/StudentService";

const CreateStudent = () => {
  const initialStudentState = {
    id: null,
    name: "",
    serie: "",
    email: ""
  };
  const [student, setStudent] = useState(initialStudentState);
  const [submitted, setSubmitted] = useState(false);

  const handleInputChange = event => {
    const { name, value } = event.target;
    setStudent({ ...student, [name]: value });
  };

  const saveStudent = () => {
    var data = {
      name: student.name,
      serie: student.serie,
      email: student.email
    };

    StudentService.create(data)
      .then(response => {
        setStudent({
          id: response.data.id,
          name: response.data.name,
          serie: response.data.serie,
          email: response.data.email
        });
        setSubmitted(true);
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  const newStudent = () => {
    setStudent(initialStudentState);
    setSubmitted(false);
  };

  return (
    <div className="submit-form">
      {submitted ? (
        <div>
          <h4>You submitted successfully!</h4>
          <button className="btn btn-success" onClick={newStudent}>
            Criar
          </button>
        </div>
      ) : (
        <div className="container">
          <div className="mb-3">
            <label for="title">Nome</label>
            <input type="text" placeholder="Insira o nome do aluno" required className="form-control" id="name" />
          </div>
          <div className="mb-3">
            <label for="title">Série</label>
            <input type="text" placeholder="Insira a série do aluno" required className="form-control" id="serie" />
          </div>
          <div className="mb-3">
            <label for="title">Email</label>
            <input type="text" placeholder="Insira o email do aluno" required className="form-control" id="email" />
          </div>
          <div className="mb-3">
              <button type="submit" className="btn btn-primary" onClick={saveStudent}>Criar</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default CreateStudent;