import React, { useState } from "react";
import BookDataService from "../services/BookService";

const AddBook = () => {
  const initialBookState = {
    id: null,
    title: ""
  };
  const [book, setBook] = useState(initialBookState);
  const [submitted, setSubmitted] = useState(false);

  const handleInputChange = event => {
    const { name, value } = event.target;
    setBook({ ...book, [name]: value });
  };

  const saveBook = () => {
    var data = {
      title: book.title,
    };

    BookDataService.create(data)
      .then(response => {
        setBook({
          id: response.data.id,
          title: response.data.title,
        });
        setSubmitted(true);
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  const newBook = () => {
    setBook(initialBookState);
    setSubmitted(false);
  };

  return (
    <div className="submit-form">
      {submitted ? (
        <div>
          <h4>You submitted successfully!</h4>
          <button className="btn btn-success" onClick={newBook}>
            Criar
          </button>
        </div>
      ) : (
        <div className="container">
          <div className="mb-3">
            <label for="title">Título</label>
            <input type="text" placeholder="Insira o título do livro" required className="form-control" id="title" />
          </div>
          <div className="mb-3">
              <button type="submit" className="btn btn-primary" onClick={saveBook}>Criar</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default AddBook;