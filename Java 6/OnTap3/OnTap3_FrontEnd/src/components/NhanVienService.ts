import axios from 'axios'

const API_BASE_URL = 'http://localhost:8080/api/nhan-vien'

export async function getAllNhanVien() {
  // 1. Tạo cấu hình đăng nhập (Basic Auth)
  const config = {
    auth: {
      username: 'TH03089', // Thay bằng username trong SecurityConfig.java
      password: 'SD20202', // Thay bằng password trong SecurityConfig.java
    },
  }

  // 2. Gửi request kèm config
  // Lưu ý: Nếu URL endpoint của bạn không phải là root, hãy chắc chắn đường dẫn đúng
  // Ví dụ: `${API_BASE_URL}` hoặc `${API_BASE_URL}/page` tùy backend
  try {
    const response = await axios.get(API_BASE_URL, config)
    console.log('Fetched NhanVien data:', response.data)
    return response.data
  } catch (error) {
    console.error('Lỗi gọi API:', error)
    return [] // Trả về mảng rỗng để không nổ lỗi màn hình
  }
}
