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

	public static void alias(String[] a) {		
			int y = 90;
			int x = y;
			int m = 70;
			int n = m;
			m = m + 1;
			System.out.prinltn(n + x);
			Main p =new Main();
			Main q = p;
			p.obj = 4;
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

public ArrayList<StatElement> getCurrentAlarmsStatList(boolean bFilter) throws Exception
	{
		ArrayList<StatElement> myStats = new ArrayList<StatElement>();

		// stop straight away of root features are disabled
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(m_context);
		boolean permsNotNeeded = sharedPrefs.getBoolean("ignore_system_app", false);		

		ArrayList<StatElement> myAlarms = null;

		if (sharedPrefs.getBoolean("force_alarms_api", false))
		{
			Log.i(TAG, "Setting set to force the use of the API for alarms");
			BatteryStatsProxy mStats = BatteryStatsProxy.getInstance(m_context);
			int statsType = 0;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
			{
				statsType = BatteryStatsTypesLolipop.STATS_CURRENT;
			}
			else
			{
				statsType = BatteryStatsTypes.STATS_CURRENT;
			}		
			
			myAlarms = mStats.getWakeupStats(m_context, statsType);
		}
		else
		{
			// use root if available as root delivers more data
			if (SysUtils.hasBatteryStatsPermission(m_context) && AlarmsDumpsys.alarmsAccessible())
			{
				myAlarms = AlarmsDumpsys.getAlarms(!SysUtils.hasDumpsysPermission(m_context));//, false);			
			}
			else if (permsNotNeeded || SysUtils.hasBatteryStatsPermission(m_context))
			{
				Log.i(TAG, "Accessing Alarms in API mode as dumpsys has failed");
				BatteryStatsProxy mStats = BatteryStatsProxy.getInstance(m_context);
				int statsType = 0;
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
				{
					statsType = BatteryStatsTypesLolipop.STATS_CURRENT;
				}
				else
				{
					statsType = BatteryStatsTypes.STATS_CURRENT;
				}		

				myAlarms = mStats.getWakeupStats(m_context, statsType);
			}
			else
			{
				return myStats;
			}
		}
		
		ArrayList<Alarm> myRetAlarms = new ArrayList<Alarm>();
		// if we are using custom ref. always retrieve "stats current"

		// sort @see
		// com.asksven.android.common.privateapiproxies.Walkelock.compareTo

		long elapsedRealtime = SystemClock.elapsedRealtime();
		for (int i = 0; i < myAlarms.size(); i++)
		{
			Alarm alarm = (Alarm) myAlarms.get(i);
			if (alarm != null)
			{
				if ((!bFilter) || ((alarm.getWakeups()) > 0))
				{
					alarm.setTimeRunning(elapsedRealtime);
					myRetAlarms.add(alarm);
				}
			}
		}

		Collections.sort(myRetAlarms);

		for (int i = 0; i < myRetAlarms.size(); i++)
		{
			myStats.add((StatElement) myRetAlarms.get(i));
		}

		if (LogSettings.DEBUG)
		{
			Log.d(TAG, "Result " + myStats.toString());
		}

		return myStats;

	}

    public Main(Activity activity, Type type) {

        if (activity == null) {

            throw new IllegalArgumentException(TAG + ERROR_ACTIVITYNULL);

        }

        this.mActivity = activity;
        this.mType = type;

        mLayoutInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mViewGroup = (ViewGroup) activity
                .findViewById(android.R.id.content);

        if (type == Type.STANDARD) {

            mToastView = mLayoutInflater.inflate(
                    R.layout.super_toast, mViewGroup, false);

        } else if (type == Type.BUTTON) {

            mToastView = mLayoutInflater.inflate(
                    R.layout.sat_button, mViewGroup, false);

            mButton = (Button) mToastView
                    .findViewById(R.id.button);

            mDividerView = mToastView
                    .findViewById(R.id.divider);

            mButton.setOnClickListener(mButtonListener);

        } else if (type == Type.PROGRESS) {

            mToastView = mLayoutInflater.inflate(R.layout.sat_progress_circle,
                    mViewGroup, false);

            mProgressBar = (ProgressBar) mToastView
                    .findViewById(R.id.progress_bar);

        } else if (type == Type.PROGRESS_HORIZONTAL) {

            mToastView = mLayoutInflater.inflate(R.layout.sat_progress_horizontal,
                    mViewGroup, false);

            mProgressBar = (ProgressBar) mToastView
                    .findViewById(R.id.progress_bar);

        }

        mMessageTextView = (TextView) mToastView
                .findViewById(R.id.message_textview);

        mRootLayout = (LinearLayout) mToastView
                .findViewById(R.id.root_layout);

    }


    private void cancelSoftwareAnimations() {
        if (mAnimRadius != null) {
            mAnimRadius.cancel();
            mAnimRadius = null;
        }

        if (mAnimOpacity != null) {
            mAnimOpacity.cancel();
            mAnimOpacity = null;
        }

        if (mAnimX != null) {
            mAnimX.cancel();
            mAnimX = null;
        }

        if (mAnimY != null) {
            mAnimY.cancel();
            mAnimY = null;
        }
    }

    public void cancelDrag() {
        if (mDragState == DRAGGING) {
            mDragScroller.stopScrolling(true);
            destroyFloatView();
            clearPositions();
            adjustAllItems();

            if (mInTouchEvent) {
                mDragState = STOPPED;
            } else {
                mDragState = IDLE;
            }
        }
    }

	@Override
	public void cast( final Hero user, int dst ) {

		if (isEquipped( user )) {
			if (quantity == 1 && !this.doUnequip( user, false, false )) {
				return;
			}
		}
		
		super.cast( user, dst );
	}

public static int cast1( int from, int to, boolean magic, boolean hitChars ) {
		
		int w = Level.WIDTH;
		
		int x0 = from % w;
		int x1 = to % w;
		int y0 = from / w;
		int y1 = to / w;
		
		int dx = x1 - x0;
		int dy = y1 - y0;
		
		int stepX = dx > 0 ? +1 : -1;
		int stepY = dy > 0 ? +1 : -1;
		
		dx = Math.abs( dx );
		dy = Math.abs( dy );
		
		int stepA;
		int stepB;
		int dA;
		int dB;
		
		if (dx > dy) {
			
			stepA = stepX;
			stepB = stepY * w;
			dA = dx;
			dB = dy;

		} else {
			
			stepA = stepY * w;
			stepB = stepX;
			dA = dy;
			dB = dx;

		}

		distance = 1;
		trace[0] = from;
		
		int cell = from;
		
		int err = dA / 2;
		while (cell != to || magic) {
			
			cell += stepA;
			
			err += dB;
			if (err >= dA) {
				err = err - dA;
				cell = cell + stepB;
			}
			
			trace[distance++] = cell;
			
			if (!Level.passable[cell] && !Level.avoid[cell]) {
				return trace[--distance - 1];
			}
			
			if (Level.losBlocking[cell] || (hitChars && Actor.findChar( cell ) != null)) {
				return cell;
			}
		}
		
		trace[distance++] = cell;
		
		return to;
	}

public void init(int width, int height, int levnum) {
        int ncols;
        int colsPerSide;
        this.levnum = levnum;

        int cx = width/2;  // center for drawing; not same as where z-axis goes to.
        int cy = height * 25/60;  // buttons at bottom of screen, so "center" is  usually a little high of actual screen center

        zpull_x = width/2;  // where z-axis goes; default z pull to be just low of center.
        zpull_y = height *33/60;

        continuous = false;
        boolean firsttime = true;
        int x=0, y=0, oldx=0, oldy=0;
        exesct = 6 + (int)(1.5*levnum);
        exesCanMove = (levnum != 1);
        if (levnum < FIRST_SPIKE_LEVEL)
            spikespct = (float)0;
        else if (levnum < FIRST_SPIKE_LEVEL +1)
            spikespct = (float) 0.5;
        else if (levnum < FIRST_SPIKE_LEVEL*2)
            spikespct = (float) 0.75;
        else spikespct = (float) 1;
        float rad_dist;
        float step;
        int radius = (int)(width*0.48); // consistent-ish radius for levels that have one
        columns.clear();

        // if we run out of screens....cycle
        int screennum = (levnum-1) % NUMSCREENS;
        //screennum=9;

        switch (screennum) {
            case 0:	// circle
                ncols = 16;
                continuous = true;
                rad_dist = (float) (Math.PI * 2);
                step = rad_dist/(ncols);
                for (float rads=0; rads < rad_dist+step/2; rads+=step)
                {
                    x = cx - (int)(Math.sin(rads) * radius * .94);
                    y = cy - (int)(Math.cos(rads) * radius *.99);
                    if (firsttime){
                        firsttime = false;
                    }
                    else {
                        Column col = new Column(oldx, oldy, x, y);
                        columns.add(col);
                    }
                    oldx = x;
                    oldy = y;
                }
                break;

            case 1: // square
                continuous = true;
                ncols = 16;
                colsPerSide = ncols/4;
                radius = (int)(width*0.43); // smaller radius for square
                int cwid = radius*2/colsPerSide;
                int i;
                // left
                for (x = cx - radius, y = cy-radius, i=0; i < colsPerSide; y+=cwid, i++){
                    if (firsttime){
                        firsttime = false;
                    }
                    else {
                        Column col = new Column(oldx, oldy, x, y);
                        columns.add(col);
                    }
                    oldx = x;
                    oldy = y;
                }
                // bottom
//                for (x = cx - radius, y = cy+radius; x < cx+radius; x+=(radius*2/colsPerSide)){
                for (i=0; i < colsPerSide; i++, x+=cwid){
                    Column col = new Column(oldx, oldy, x, y);
                    columns.add(col);
                    oldx = x;
                    oldy = y;
                }
                // right
//                for (x = cx + radius, y = cy+radius; y > cy-radius; y-=(radius*2/colsPerSide)){
                for (i=0; i< colsPerSide; i++, y-=cwid){
                    Column col = new Column(oldx, oldy, x, y);
                    columns.add(col);
                    oldx = x;
                    oldy = y;
                }
                // top
//                for (x = cx + radius, y = cy-radius; x >= cx-radius; x-=(radius*2/colsPerSide)){
                for (i=0; i<colsPerSide; i++, x-=cwid){
                    Column col = new Column(oldx, oldy, x, y);
                    columns.add(col);
                    oldx = x;
                    oldy = y;
                }
                columns.add(new Column(oldx, oldy, x, y));
                break;

            case 2: // plus
                continuous = true;
                int colwidth = width/5;
                zpull_x=cx;
                zpull_y= cy+(int)(colwidth*0.8);
                x = oldx = cx-colwidth;
                y = oldy = cy - colwidth;
                x-= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldx = x;
                y+= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldy = y;
                y+= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldy = y;
                x+= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldx = x;
                y+= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldy = y;
                x+= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldx = x;
                x+= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldx = x;
                y-= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldy = y;
                x+= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldx = x;
                y-= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldy = y;
                y-= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldy = y;
                x-= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldx = x;
                y-= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldy = y;
                x-= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldx = x;
                x-= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldx = x;
                y+= colwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldy = y;
                break;

            case 3: // infinitybinocularthing
                ncols = 8;
                zpull_y = cy;//*41/40;
                continuous = true;
                rad_dist = (float) (Math.PI * 2);
                step = rad_dist/(ncols);
                int origx=0;
                int cxc = (int)((float)cx * .44);
                //radius = (int)((cx - cxc)*0.81f);
                radius = (int)((cx - cxc)*0.89f);
                i = 0;
                for (float rads=-1.5f*step; rads < rad_dist-step*1.5; rads+=step)
                {
                    x = cxc - (int)(Math.sin(rads) * radius * .90);
                    y = cy - (int)(Math.cos(rads) * radius);
                    if (i == 0 || i == 7){
                        y = cy - (int)(Math.cos(rads) * radius * 1.3);
                        x = cxc - (int)(Math.sin(rads) * radius * .85);
                    }
                    if (i==0){
                        origx = x;
                    }
                    else {
                        Column col = new Column(oldx, oldy, x, y);
                        columns.add(col);
                    }
                    oldx = x;
                    oldy = y;
                    i++;
                }
                cxc = (int)((float)cx * 1.56);
                i = 0;
                for (float rads=+2.5f*step; rads < rad_dist+1.5*step; rads+=step)
                {
                    x = cxc - (int)(Math.sin(rads) * radius * .90);
                    y = cy - (int)(Math.cos(rads) * radius);
                    if (i == 0 || i == 7){
                        y = cy - (int)(Math.cos(rads) * radius * 1.3);
                        x = cxc - (int)(Math.sin(rads) * radius * .85);
                    }
                    Column col = new Column(oldx, oldy, x, y);
                    columns.add(col);
                    oldx = x;
                    oldy = y;
                    i++;
                }
                columns.add(new Column(oldx, oldy, origx, y));
                break;

            case 4: // triangle
                continuous = true;
                //radius = 320;
                ncols = 15;
                colsPerSide = ncols/3;
                zpull_x = width/2;
                zpull_y = height *29/60;
                // left
                for (x = cx, y = cy-radius; y < cy+radius*4/5; y+=(radius*4/3/colsPerSide),x-=radius*3/4/colsPerSide){
                    if (firsttime){
                        firsttime = false;
                    }
                    else {
                        Column col = new Column(oldx, oldy, x, y);
                        columns.add(col);
                    }
                    oldx = x;
                    oldy = y;
                }
                // bottom
                firsttime = true;
                int targx = cx + (cx-oldx);
                for (x = oldx, y = oldy; x < targx; x+=(radius*4/3/colsPerSide)){
                    if (firsttime){
                        firsttime = false;
                    }
                    else {
                        Column col = new Column(oldx, oldy, x, y);
                        columns.add(col);
                    }
                    oldx = x;
                    oldy = y;
                }
                // right
                for (; y >= cy-radius; y-=(radius*4/3/colsPerSide),x-=radius*3/4/colsPerSide+4){
                    Column col = new Column(oldx, oldy, x, y);
                    columns.add(col);
                    oldx = x;
                    oldy = y;
                }
                // force rounding error mess to go away
                Column lastcol = columns.get(columns.size()-1);
                lastcol.setFrontPoint2(columns.get(0).getFrontPoint1());
                break;

            case 5: // straight, angled V
                ncols = 16;
                // need a different z-pull for a board using this screen
                zpull_x = cx;
                zpull_y = height /4;
                int yhi = cy/3;
                int ylow = cy*5/3;
                int ydist = ylow-yhi;
                int xdist = cx * 3/2;  // 1/4 of cx on each side
                for (x = 0, y = ylow; y >= yhi; x+= xdist/(ncols), y-=ydist/(ncols/2)){
                    if (firsttime){
                        firsttime = false;
                    }
                    else {
                        columns.add(0, new Column(cx-x, y, cx-oldx, oldy));
                        columns.add(new Column(cx+oldx, oldy, cx+x, y));
                    }
                    oldx = x;
                    oldy = y;
                }
                break;

            case 6: // jagged V
                zpull_x = width/2;
                zpull_y = height /5;
                int total_cols = 15;  // must be ODD
                int xcolwidth = (int) Math.floor(width/(total_cols/2 + 1)); // should be enough
                int ycolwidth = xcolwidth * 5/4;
                int ystart;
                x = oldx = cx - (int)(xcolwidth*3.5);
                y = oldy = ystart = cy - ycolwidth;
                y+=ycolwidth;
                columns.add(new Column(oldx, oldy, x, y));
                oldy = y;
                while (y < ystart + ycolwidth*4){
                    x+=xcolwidth;
                    columns.add(new Column(oldx, oldy, x, y));
                    oldx=x;
                    y+=ycolwidth;
                    columns.add(new Column(oldx, oldy, x, y));
                    oldy=y;
                }
                while (y > ystart){
                    x+=xcolwidth;
                    columns.add(new Column(oldx, oldy, x, y));
                    oldx=x;
                    y-=ycolwidth;
                    columns.add(new Column(oldx, oldy, x, y));
                    oldy=y;
                }
                break;

            case 7:	// U
                // arc portion
                ncols = 8;
                radius *= .9;
                rad_dist = (float) (Math.PI); // half circ
                step = rad_dist/(ncols);
                zpull_x = width/2;
                zpull_y = height*17/28;
                int xradius=0, orgy=0, straightstepdist=0;
                for (double rads=0; rads < Math.PI+step/2; rads+=step)
                {
                    x = cx - (int)(Math.cos(rads) * radius * .95);
                    y = cy * 12/10 + (int)(Math.sin(rads) * radius);
                    if (firsttime){
                        firsttime = false;
                        xradius = cx - x;
                        orgy = y;
                        straightstepdist = (int)(Math.sin(step) * radius);
                    }
                    else {
                        columns.add(new Column(oldx, oldy, x, y));
                    }
                    oldx = x;
                    oldy = y;
                }
                for (int j=0; j<3; j++)
                    columns.add(0, new Column(cx-xradius, orgy-straightstepdist*(j+1), cx-xradius, orgy-straightstepdist*j));
                for (int j=0; j<3; j++)
                    columns.add(new Column(cx+xradius, orgy-straightstepdist*j, cx+xradius, orgy-straightstepdist*(j+1)));
                break;

            case 8: // straight line
                ncols = 14;
                // need a different z-pull for a board using this screen
                zpull_x = width/2;
                zpull_y = height /4;
                y = height * 4/7;
                for (x = width *1/(ncols+1); x < width * (1+ncols)/(ncols+2); x+= width/(ncols+2)){
                    if (firsttime){
                        firsttime = false;
                    }
                    else {
                        Column col = new Column(oldx, oldy, x, y);
                        columns.add(col);
                    }
                    oldx = x;
                    oldy = y;

                }
                break;

            case 9: // crossing infinity
                ncols = 10;
                zpull_y = cy*38/40;
                continuous = true;
                rad_dist = (float) (Math.PI * 2);
                step = rad_dist/(ncols);
                origx=0;
                orgy=0;
                cxc = (int)((float)cx * .44);
                radius = (int)((cx - cxc)*0.88f);
                firsttime = true;
                for (float rads=-1.5f*step; rads < rad_dist-step*3.5; rads+=step)
                {
                    x = cxc - (int)(Math.sin(rads) * radius * .90);
                    y = cy - (int)(Math.cos(rads) * radius);
//                    if (i == 0 || i == 7){
//                        y = cy - (int)(Math.cos(rads) * radius * 1.3);
//                        x = cxc - (int)(Math.sin(rads) * radius * .85);
//                    }
                    if (firsttime){
                        origx = x;
                        orgy = y;
                        firsttime = false;
                    }
                    else {
                        Column col = new Column(oldx, oldy, x, y);
                        columns.add(col);
                    }
                    oldx = x;
                    oldy = y;
                }
                x = cx;
                y = cy;
                columns.add(new Column(oldx, oldy, x, y));
                oldx = x;
                oldy = y;

                cxc = (int)((float)cx * 1.56);
                for (float rads=+1.5f*step; rads > -rad_dist+3.5*step; rads-=step)
                {
                    x = cxc - (int)(Math.sin(rads) * radius * .90);
                    y = cy - (int)(Math.cos(rads) * radius);
//                    if (i == 0 || i == 7){
//                        y = cy - (int)(Math.cos(rads) * radius * 1.3);
//                        x = cxc - (int)(Math.sin(rads) * radius * .85);
//                    }
                    Column col = new Column(oldx, oldy, x, y);
                    columns.add(col);
                    oldx = x;
                    oldy = y;
                }
                x = cx;
                y = cy;
                columns.add(new Column(oldx, oldy, x, y));
                oldx = x;
                oldy = y;
                columns.add(new Column(oldx, oldy, origx, orgy));
                break;

        }
    }

    private boolean appInstalledOrNot(String uri,PackageManager pm) {
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

public List<Test> asTestList(Description description) {
        if (description.isTest()) {
            return Arrays.asList(asTest(description));
        } else {
            List<Test> returnThis = new ArrayList<Test>();
            for (Description child : description.getChildren()) {
                returnThis.add(asTest(child));
            }
            return returnThis;
        }
    }
}
