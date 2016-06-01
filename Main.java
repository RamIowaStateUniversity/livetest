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

public SuperActivityToast(Activity activity, Style style) {

        if (activity == null) {

            throw new IllegalArgumentException(TAG + ERROR_ACTIVITYNULL);

        }

        this.mActivity = activity;

        mLayoutInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mViewGroup = (ViewGroup) activity
                .findViewById(android.R.id.content);

        mToastView = mLayoutInflater.inflate(R.layout.super_toast,
                mViewGroup, false);

        mMessageTextView = (TextView) mToastView
                .findViewById(R.id.message_textview);

        mRootLayout = (LinearLayout) mToastView
                .findViewById(R.id.root_layout);

        this.setStyle(style);

    }

    /**
     * Instantiates a new {@value #TAG} with a type.
     *
     * @param activity {@link android.app.Activity}
     * @param type     {@link com.github.johnpersano.supertoasts.SuperToast.Type}
     */
    public SuperActivityToast(Activity activity, Type type) {

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

}
