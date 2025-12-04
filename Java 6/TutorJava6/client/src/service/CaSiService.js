// liet ke cac api theo backend

const API = "http://localhost:8080/api/singer/management"

export const getDanhSachCaSi = async () => {
    const response = await fetch(API);

    if (!response.ok) {
        throw new Error("Loi lay danh sach ca si");
    }
    return await response.json();
}

//phan trang
//method: ko truyen phuong http nao thi default la GET
export const getDanhSachCaSi_PhanTrang = async (pageNo, pageSize) => {
    //truyen gia tri tren duong dan: ${bien}
    const res = await fetch(`${API}/phan-trang?pageNo1=${pageNo}&pageSize1=${pageSize}`);

    if (!res.ok) {
        throw new Error("Loi phan trang ca si");
    }
    return await res.json();
}

//http://localhost:8080/api/singer/management/delete/{{id}}

export const deleteCaSi = async (id) => {
    //truyen gia tri tren duong dan: ${bien}
    const res = await fetch(`${API}/delete/${id}`, {
        method: "DELETE"
    }
    );

    if (!res.ok) {
        throw new Error("Loi xoa ca si");
    }
}

//detail ca si
// http://localhost:8080/api/singer/management/detail/{{id}}
export const getDetailCaSi = async (id) => {
    const res = await fetch(`${API}/detail/${id}`);

    if (!res.ok) {
        throw new Error("Loi lay chi tiet ca si");
    }
    return await res.json();
}

//them ca si
//http://localhost:8080/api/singer/management/add
export const addCaSi = async (data) => {
    const res = await fetch(`${API}/add`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    if (!res.ok) {
        throw new Error("Loi them ca si");
    }
    return nulll; //do BE ko return
}

//update ca si
//http://localhost:8080/api/singer/management/update/{{id}}
export const updateCaSi = async (data, id) => {
    const res = await fetch(`${API}/update/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    if (!res.ok) {
        throw new Error("Loi cap nhat ca si");
    }
    return nulll; //do BE ko return
}