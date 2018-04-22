package com.huasheng.decryptor.activity;

import com.huasheng.decryptor.R;
import com.huasheng.decryptor.util.RSAUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DecryptActivity extends Activity implements OnClickListener{
	
	private EditText encryptET;
	private Button decryptBtn;
	private EditText outputET;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.activity_decrypt);
		
		this.intComponents();
	}
	
	private void intComponents(){
		this.encryptET = (EditText)this.findViewById(R.id.encrypt_et);
		this.decryptBtn = (Button)this.findViewById(R.id.decrypt_btn);
		this.outputET = (EditText)this.findViewById(R.id.output_et);
		this.decryptBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.decrypt_btn){
			this.decrypt();
		}
	}
	
	private void decrypt(){
		
		//校验
		String encryptStr = this.encryptET.getText().toString().trim();
		if(encryptStr == null || encryptStr.length() == 0){
			Toast.makeText(this, "请先粘贴密文", Toast.LENGTH_LONG).show();
			return;
		}
		
		//解密
		String privateKeyStr = "30820277020100300d06092a864886f70d0101010500048202613082025d02010002818100a5d2892a01182448da82ee00a1ffd95806f082a8d18371969538f8271d4d0f141fe1e301b1b931a8c12a0f4d9790e9bf9e3e9870af9f5d79469c983858a5c826c04d247e10eaa79e0998e2277a4a2fbb69451b48d8c876981afeae80deaf64f441890fbfe546b70bf26bbf7faaad13f4dd58b4f7956de10ba06c839ddfbe72ed0203010001028180386557330af63bf8ef201273f2a4c23596fe9b617108244c9369b1b413da0e63c8c8195d8d25300775d2a92bb97d96920deeba07b12321364ad69d4aa493957353da3c5b69cd683aaf73bca9c8ff83174f1412a8c5af9e398bf501092f30989423dc214f9a24b0c82c6c0039978185defa2622001f01466acf1d431cadc7eafd024100dfbc4f8a7f5b360b9101ac3175d57c487f9842523301ee4d71cce0e3f1b92d3573ddc4e05ec65c1395528a7f72a85a3792cddca9d3d180f5889396f7486a189b024100bdbc3bab9072c396c531aa9130b1cd800119e7bc4f0e3cbfecf6cd64819ef715c7275b52ccccf9d3d66509cc10fa5c7e97788bfdc2837e3054d5a01fa63e0717024100b4aee1159a3050ff1a45dd87bbff82751206265937b4317cfd905576003a699627524bb25f1796388278bc3c9c9df4edb872e3e78a9640ad0975178ff03d2e4d0240396ec72931ba6b9631eeccd0d24aa008054d5026624fcef7d1c50b7b46f95595d40907cdd28ca17b0c44dd68562352e846f5887ab1ec97c00d8df4c05fd41ad502410086961fc97717c534e78c091cf0c1cbbd5f62d867d93199943beb28980a79e9e3f5e7a5241c5892a591c1cc010be43fad30b82dd91e956b8481f5244d128a20ea";
		try {
			String outputStr = RSAUtils.decrypt(encryptStr, privateKeyStr);
			this.outputET.setText(outputStr);
		}catch(Exception e) {
			Toast.makeText(this, "解密失败，请检查密文是否正确", Toast.LENGTH_LONG).show();
			return;
		}
		
	}
}
