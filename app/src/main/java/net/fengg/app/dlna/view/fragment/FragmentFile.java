package net.fengg.app.dlna.view.fragment;

import fi.iki.elonen.SimpleWebServer;
import butterknife.ButterKnife;
import butterknife.InjectView;
import net.fengg.app.dlna.R;
import net.fengg.app.dlna.util.NetUtil;
import android.text.TextUtils;
import net.fengg.app.dlna.view.base.BaseFragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class FragmentFile extends BaseFragment implements OnCheckedChangeListener {
	@InjectView(R.id.tb_server)
	protected ToggleButton tb_server;
	@InjectView(R.id.tv_tips)
	protected TextView tv_tips;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_file, container, false);
		ButterKnife.inject(this, view);
		initView();
		init();
		return view;
	}
	
	public void initView(){
		tb_server.setOnCheckedChangeListener(this);
	}
	
	public void init() {
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		cancelBaseDialog();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if(isChecked) {
			String ip = NetUtil.getIp();
			if(TextUtils.isEmpty(ip)) {
                tv_tips.setVisibility(View.VISIBLE);
                tv_tips.setText(R.string.error);
                return;
            }
			int port = 9999;
			SimpleWebServer.startServer(ip, port, 
					Environment.getExternalStorageDirectory().getPath());
			tv_tips.setVisibility(View.VISIBLE);
			tv_tips.setText(getString(R.string.input) + "\n" + "http://" + ip + ":" + port);
		}else{
			SimpleWebServer.stopServer();
			tv_tips.setVisibility(View.INVISIBLE);
		}
	}
}
