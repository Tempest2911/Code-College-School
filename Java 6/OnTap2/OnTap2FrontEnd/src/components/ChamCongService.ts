import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/cham-cong";

export async function getAllChamCong() {
const response = await axios.get(`${API_BASE_URL}`);
console.log("Fetched ChamCong data:", response.data);
return response.data;
}   