import React, { useState, useEffect, useMemo, useRef } from "react";
import Pagination from "@material-ui/lab/Pagination";
import BookDataService from "../services/BookService";
import { useTable } from "react-table";


const BookList = (props) => {
  const [books, setBooks] = useState([]);
  const [searchTitle, setSearchTitle] = useState("");
  const booksRef = useRef();

  const [page, setPage] = useState(1);
  const [count, setCount] = useState(0);
  const [pageSize, setPageSize] = useState(3);

  const pageSizes = [3, 6, 9];

  booksRef.current = books;

  const onChangeSearchTitle = (e) => {
    const searchTitle = e.target.value;
    setSearchTitle(searchTitle);
  };

  const getRequestParams = (searchTitle, page, pageSize) => {
    let params = {};

    if (searchTitle) {
      params["title"] = searchTitle;
    }

    if (page) {
      params["page"] = page - 1;
    }

    if (pageSize) {
      params["size"] = pageSize;
    }

    return params;
  };

  const retrieveBooks = () => {
    const params = getRequestParams(searchTitle, page, pageSize);

    BookDataService.getAll(params)
      .then((response) => {
        const { books, totalPages } = response.data;

        setBooks(books);
        setCount(totalPages);

        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  useEffect(retrieveBooks, [page, pageSize]);

  const refreshList = () => {
    retrieveBooks();
  };

  const findByTitle = () => {
    setPage(1);
    retrieveBooks();
  };

  const openBook = (rowIndex) => {
    const id = booksRef.current[rowIndex].id;

    props.history.push("/livros/" + id);
  };

  const deleteBook = (rowIndex) => {
    const id = booksRef.current[rowIndex].id;

    BookDataService.remove(id)
      .then((response) => {
        props.history.push("/livros");

        let newBooks = [...booksRef.current];
        newBooks.splice(rowIndex, 1);

        setBooks(newBooks);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const handlePageChange = (event, value) => {
    setPage(value);
  };

  const handlePageSizeChange = (event) => {
    setPageSize(event.target.value);
    setPage(1);
  };

  const columns = useMemo(
    () => [
      {
        Header: "ID",
        accessor: "bookId",
      },
      {
        Header: "Título",
        accessor: "title",
      },
      {
        Header: "Categoria",
        accessor: "category",
      },
      {
        Header: "Status",
        accessor: "borrow",
      },
      {
        Header: "Actions",
        accessor: "actions",
        Cell: (props) => {
          const rowIdx = props.row.id;
         // const rowIdx = props.value.id
          console.log("teste" + rowIdx)
          return (
            <div>
              <span onClick={() => openBook(rowIdx)}>
                <i className="far fa-edit action mr-2"></i>
              </span>

              <span onClick={() => deleteBook(rowIdx)}>
                <i className="fas fa-trash action"></i>
              </span>              
            </div>
            
          );
        },
      },
    ],
    []
  );

  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    rows,
    prepareRow,
  } = useTable({
    columns,
    data: books,
  });

  return (
    <div className="list row">
      <div className="col-md-8">
        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Search by title"
            value={searchTitle}
            onChange={onChangeSearchTitle}
          />
          <div className="input-group-append">
            <button
              className="btn btn-outline-secondary"
              type="button"
              onClick={findByTitle}
            >
              Search
            </button>
          </div>
        </div>
      </div>

      <div className="col-md-12 list">
        <div className="mt-3">
          {"Items per Page: "}
          <select onChange={handlePageSizeChange} value={pageSize}>
            {pageSizes.map((size) => (
              <option key={size} value={size}>
                {size}
              </option>
            ))}
          </select>

          <Pagination
            className="my-3"
            count={count}
            page={page}
            siblingCount={1}
            boundaryCount={1}
            variant="outlined"
            shape="rounded"
            onChange={handlePageChange}
          />
        </div>

        <table
          className="table table-striped table-bordered"
          {...getTableProps()}
        >
          <thead>
            {headerGroups.map((headerGroup) => (
              <tr {...headerGroup.getHeaderGroupProps()}>
                {headerGroup.headers.map((column) => (
                  <th {...column.getHeaderProps()}>
                    {column.render("Header")}
                  </th>
                ))}
              </tr>
            ))}
          </thead>
          <tbody {...getTableBodyProps()}>
            {rows.map((row, i) => {
              prepareRow(row);
              return (
                <tr {...row.getRowProps()}>
                  {row.cells.map((cell) => {
                    return (
                      <td {...cell.getCellProps()}>{cell.render("Cell")}</td>
                    );
                  })}
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default BookList;