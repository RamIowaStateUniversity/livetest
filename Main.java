import java.util.Scanner;

public class Main {
	public static void live(String[] a) {		
			Scanner sc=new Scanner(System.in);
			int y=90;
			int b=1;
			int j=0;
			b=b+2;
			if(y==0) {
				y++;
			}
			while(j<2) {
				System.out.println(compute(y));
				j++;				
			}
	}
	
	public void setLocationWorld(int tile_x, int tile_y) {
		Log.d("setlocation", "start");
		synchronized(load_new_tiles) {
			if(load_new_tiles) loading.stop();
			loading = null;
		}
		
		int w_half = TILE_WIDTH / 2;
		int h_half = TILE_HEIGHT / 2;
		
		Rect new_g_extent = new Rect(global_tile_extent);
		new_g_extent.offsetTo(w_half + tile_x, h_half + tile_y);
		
		Rect new_px_extent = new Rect(local_pixel_extent);
		new_px_extent.offsetTo(w_half * 32 + (TILE_WIDTH % 2) * 16, h_half * 32 + (TILE_HEIGHT % 2) * 16);
		
		synchronized(buffer_cur) {
			canvas.drawColor(0xFF000000);
		}
		Bitmap buffer = buffer_off;
		canvas.setBitmap(buffer);
		drawAllTiles(canvas, new_g_extent);
		
		synchronized(buffer_cur) {
			buffer_off = buffer_cur;
			buffer_cur = buffer;
			global_tile_extent = new_g_extent;
			local_pixel_extent = new_px_extent;
		}
		
		synchronized(load_new_tiles) {
			load_new_tiles = false;
		}
		Log.d("setlocation", "stop");

	}
	   private void  zwitch(Attributes attributes) throws SAXException
   {
      debug("<switch>");

      if (currentElement == null)
         throw new SAXException("Invalid document. Root element must be <svg>");
      SVG.Switch  obj = new SVG.Switch();
      obj.document = svgDocument;
      obj.parent = currentElement;
      parseAttributesCore(obj, attributes);
      parseAttributesStyle(obj, attributes);
      parseAttributesTransform(obj, attributes);
      parseAttributesConditional(obj, attributes);
      currentElement.addChild(obj);
      currentElement = obj;
   }
}
