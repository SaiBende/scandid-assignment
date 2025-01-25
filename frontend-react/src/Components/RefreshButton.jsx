

const RefreshButton = () => {
  const handleRefresh = () => {
    window.location.reload(); // Reload the entire page
  };

  return (
    <button
      onClick={handleRefresh}
      className="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600"
    >
      Refresh the Page to See the Data
    </button>
  );
};

export default RefreshButton;
