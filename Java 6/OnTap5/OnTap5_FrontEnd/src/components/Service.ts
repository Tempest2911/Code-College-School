import axios from 'axios'

const API_BASE_URL = 'http://localhost:8080/api/donDatHang'

export async function getAllDonDatHang() {
  const config = {
    auth: {
      username: 'TH03089',
      password: 'SD20202',
    },
  }

  try {
    const response = await axios.get(API_BASE_URL, config)
    console.log('Fetched DonDatHang data:', response.data)
    return response.data
  } catch (error) {
    console.error('Lỗi gọi API:', error)
    return []
  }
}
