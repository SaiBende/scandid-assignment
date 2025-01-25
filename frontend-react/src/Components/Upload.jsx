import  { useState } from "react";
import axios from "axios";

const Upload = () => {
  const [file, setFile] = useState(null);
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
    setMessage("");
    setError("");
  };

  const handleUpload = async (e) => {
    e.preventDefault();
    if (!file) {
      setError("Please select a file before uploading.");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      const response = await axios.post("http://localhost:8080/api/csv/import", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      setMessage(response.data);
      setFile(null);
    } catch (err) {
      setError(
        err.response?.data || "An error occurred while uploading the file."
      );
    }
  };

  return (
    <div className="max-w-md mx-auto mt-10 p-6 bg-white rounded-lg shadow-lg dark:bg-gray-800">
      <h2 className="text-xl font-bold mb-4 text-gray-800 dark:text-gray-100">
        Upload CSV File
      </h2>
      <form onSubmit={handleUpload}>
        <div className="mb-4">
          <label
            htmlFor="csvFile"
            className="block mb-2 text-sm font-medium text-gray-700 dark:text-gray-200"
          >
            Select CSV File
          </label>
          <input
            type="file"
            id="csvFile"
            accept=".csv"
            onChange={handleFileChange}
            className="block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4 file:rounded-lg file:border-0 file:text-sm file:font-semibold file:bg-gray-50 file:text-gray-700 hover:file:bg-gray-100 dark:file:bg-gray-700 dark:file:text-gray-300 dark:hover:file:bg-gray-600"
          />
        </div>
        {error && (
          <p className="text-sm text-red-500 mb-4">
            <strong>Error:</strong> {error}
          </p>
        )}
        {message && (
          <p className="text-sm text-green-500 mb-4">
            <strong>Success: Wait for 10 to 15 sec to Arrange and Normalize the Dataset</strong> {message}
          </p>
        )}
        <button
          type="submit"
          className="w-full py-2 px-4 text-white bg-blue-600 rounded-lg hover:bg-blue-700 dark:bg-blue-500 dark:hover:bg-blue-600"
        >
          Upload
        </button>
      </form>
    </div>
  );
};

export default Upload;
