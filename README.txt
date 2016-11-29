GROUP LEADER: Jackson Collins

STUDENTS
Name:Jackson Collins
User:jrc772
Number:4603254
Email:jrc772@uowmail.edu.au

Name:Scott Young
User:sey872
Number:4524639
Email:sey872@uowmail.edu.au


CODE

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
	Adapter used for interface


	
RUNNING APPLICATION:	
	
Application is set for MIN API 19 

