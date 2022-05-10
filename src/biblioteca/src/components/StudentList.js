import React, { useState, useEffect, useMemo, useRef } from "react";
import Pagination from "@material-ui/lab/Pagination";
import StudentService from "../services/StudentService";
import { useTable } from "react-table";

const StudentList = (props) => {
    const [students, setStudents] = useState([]);
    const [searchName, setSearchName] = useState("");
    const studentsRef = useRef();

    const [page, setPage] = useState(1);
    const [count, setCount] = useState(0);
    const [pageSize, setPageSize] = useState(3);

    const pageSizes = [3, 6, 9];

    studentsRef.current = students;

    const onChangeSearchName = (e) => {
        const searchName = e.target.value;
        setSearchName(searchName);
    };

    const getRequestParams = (searchName, page, pageSize) => {
        let params = {};

        if (searchName) {
            params["name"] = searchName;
        }

        if (page) {
            params["page"] = page - 1;
        }

        if (pageSize) {
            params["size"] = pageSize;
        }

        return params;
    };

    const retrieveStudents = () => {
        const params = getRequestParams(searchName, page, pageSize);

        StudentService.getAll(params)
            .then((response) => {
                const { students, totalPages } = response.data;

                setStudents(students);
                setCount(totalPages);

                console.log(response.data);
            })
            .catch((e) => {
                console.log(e);
            });
    };

    useEffect(retrieveStudents, [page, pageSize]);

    const refreshList = () => {
        retrieveStudents();
    };

    const findByName = () => {
        setPage(1);
        retrieveStudents();
    };

    const openStudent = (rowIndex) => {
        const id = studentsRef.current[rowIndex].id;

        props.history.push("/alunos/" + id);
    };

    const deleteStudent = (rowIndex) => {
        const id = studentsRef.current[rowIndex].id;

        StudentService.remove(id)
            .then((response) => {
                props.history.push("/alunos");

                let newStudents = [...studentsRef.current];
                newStudents.splice(rowIndex, 1);

                setStudents(newStudents);
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
                accessor: "studentId",
            },
            {
                Header: "Nome",
                accessor: "name",
            },
            {
                Header: "Série",
                accessor: "serie",
            },
            {
                Header: "Email",
                accessor: "email",
            },
            {
                Header: "Ações",
                accessor: "actions",
                Cell: (props) => {
                    const rowIdx = props.row.id;
                    console.log("teste" + rowIdx)
                    return (
                        <div>
                            <span onClick={() => openStudent(rowIdx)}>
                                <i className="far fa-edit action mr-2"></i>
                            </span>

                            <span onClick={() => deleteStudent(rowIdx)}>
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
        data: students,
    });

    return (
        <div className="container">
            <div className="input-group mb-3">
                <div className="col-md-8">
                    <input type="text" className="form-control" placeholder="Procure pelo nome do aluno" value={searchName} onChange={onChangeSearchName} />
                </div>
                <div className="col-md-4">
                    <button className="btn btn-primary" type="button" onClick={findByName}>Buscar</button>
                </div>
            </div>
            <div className="input-group mb-3">
                <div class="col-12 d-md-flex justify-content-md-end">
                    {"Items per Page: "}
                    <select onChange={handlePageSizeChange} value={pageSize}>
                        {pageSizes.map((size) => (
                            <option key={size} value={size}>
                                {size}
                            </option>
                        ))}
                    </select>
                </div>
            </div>
            <div className="col-md-12 list">
                <table
                    className="table table-bordered table-striped table-hover"
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
            <div className="input-group mb-3">
                <div className="col-12 d-md-flex justify-content-md-end">
                    <div className="mt-3">
                        <Pagination className="my-3" count={count} page={page} siblingCount={1} boundaryCount={1} variant="outlined" shape="rounded" onChange={handlePageChange} />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default StudentList;