Group Leader: Jackson Collins

Students
Name:Jackson Collins
Email:jrc772@uowmail.edu.au

Name:Scott Young
Email:sey872@uowmail.edu.au

MainActivity.java
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		Opens selected store fragments
	
	private double getRating(List<String> rating)
		returns store rating
	
	private void sortList(List<storeList> list)
		Sorts list items by distance
		
	private double getDistance(double x1, double y1, double x2, double y2)
		Gets distance between two points
		
	private void swap(List<storeList> list, int i, int j)
		Swaps a item in the list


MapsActivity.java		
	protected void onCreate(Bundle savedInstanceState)
		
	
	
	public void onMapReady(GoogleMap googleMap)

	
		
Ratings.java
	public List<String> getRatings(String storeName)
		Get's all of the ratings for a store?

	public String performNASARequest()
		gets the ratings from online
		
	public class HTMLNetControl extends AsyncTask<String, Integer, String>
		Does ...

FragmentDisplayStore.java
	private class DownloadImageTask extends AsyncTask<String,Void,Bitmap>
		Downloads the images as bitmap and sets
		
		
FragmentDisplayRating.java
	Displays ratings of popup

storeList.java
	Object of a store

ListAdapter.java
	???



Application is MIN API 19 

