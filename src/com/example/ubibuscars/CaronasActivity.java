package com.example.ubibuscars;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class CaronasActivity extends ListActivity {
	JSONArray caronas = null;
	ArrayList<HashMap<String, Object>> caronaList = new ArrayList<HashMap<String, Object>>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_caronas);
		
		ListaCaronas listarCaronas = new ListaCaronas();
		listarCaronas.execute();
		
		
		
 /*
		String readJson = CustomHttpPost.readJson(Servidor.getServidor()
				+ "/buscaCaronas.php");
		try {
			JSONArray jsonArray = new JSONArray(readJson);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String idUserAux = jsonObject.getString("id_usuario");
				
				//mostra so as caronas que nao sejam do usuario logado e ativas
				if ( (Integer.parseInt(idUserAux) != LoginActivity
						.getId_usuario()) && (jsonObject.getString("ativo").equals("1")) ) {
					String idCarona = jsonObject.getString("id_carona");
					String idUsuario = jsonObject.getString("id_usuario");
					String nome = jsonObject.getString("nome");
					String endOrigem = jsonObject.getString("endereco_origem");
					String endDestino = jsonObject
							.getString("endereco_destino");
					String horarioOrigem = jsonObject
							.getString("horario_origem");
					String horarioDestino = jsonObject
							.getString("horario_destino");
					String tipo = null;
					if (jsonObject.getString("tipo").equals("1")) {
						tipo = "Oferece";
					} else {
						tipo = "Pede";
					}

					HashMap<String, Object> map = new HashMap<String, Object>();

					map.put("id_carona", idCarona);
					map.put("id_usuario", idUsuario);
					map.put("nome", nome);
					map.put("tipo", tipo);
					map.put("endereco_origem", endOrigem);
					map.put("endereco_destino", endDestino);
					map.put("horario_origem", horarioOrigem);
					map.put("horario_destino", horarioDestino);

					if (jsonObject.getString("tipo").equals("1")) {
						map.put("imagem", R.drawable.oferece_carona);
					} else {
						map.put("imagem", R.drawable.pede_carona);
					}

					caronaList.add(map);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		ListAdapter adapter = new SimpleAdapter(this, caronaList,
				R.layout.list_item, new String[] { "nome", "endereco_origem",
						"endereco_destino", "imagem" }, new int[] {
						R.id.TextNomeUsuario, R.id.textEndOrig,
						R.id.textEndDest, R.id.imageViewTipoCarona });
		setListAdapter(adapter);
*/
		
		
		
		
		
		
		ListView lv = getListView();
		
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				ClicaCarona clicaNaCarona = new ClicaCarona();
				clicaNaCarona.execute(position);
				
			}
		});
		
/*
		// Launching new screen on Selecting Single ListItem
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				HashMap<String, Object> mapAux = new HashMap<String, Object>();
				mapAux = caronaList.get(position);

				String idCarona = (String) mapAux.get("id_carona");
				String idUsuario = (String) mapAux.get("id_usuario");
				String name = (String) mapAux.get("nome");
				String origem = (String) mapAux.get("endereco_origem");
				String destino = (String) mapAux.get("endereco_destino");
				String horarioOrigem = (String) mapAux.get("horario_origem");
				String horarioDestino = (String) mapAux.get("horario_destino");
				String tipo = (String) mapAux.get("tipo");

				// Starting new intent
				Intent in = new Intent(getApplicationContext(),
						SolicitaCaronaActivity.class);
				in.putExtra("idCarona", idCarona);
				in.putExtra("idUsuario", idUsuario);
				in.putExtra("nome", name);
				in.putExtra("endereco_origem", origem);
				in.putExtra("endereco_destino", destino);
				in.putExtra("horario_origem", horarioOrigem);
				in.putExtra("horario_destino", horarioDestino);
				in.putExtra("tipo", tipo);
				startActivity(in);

			}
		});
*/
		
		
	}
	
	
	

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
	
	
	public class ClicaCarona extends AsyncTask<Integer, Void, Void>{

		protected Void doInBackground(Integer... params) {
			
			
			HashMap<String, Object> mapAux = new HashMap<String, Object>();
			
			int position = params[0];
			mapAux = caronaList.get(position);
			
			String idCarona = (String) mapAux.get("id_carona");
			String idUsuario = (String) mapAux.get("id_usuario");
			String name = (String) mapAux.get("nome");
			String origem = (String) mapAux.get("endereco_origem");
			String destino = (String) mapAux.get("endereco_destino");
			String horarioOrigem = (String) mapAux.get("horario_origem");
			String horarioDestino = (String) mapAux.get("horario_destino");
			String tipo = (String) mapAux.get("tipo");
			String vagasDisponiveis = (String) mapAux.get("vagas_disponiveis");

			// Starting new intent
			Intent in = new Intent(getApplicationContext(),
					SolicitaCaronaActivity.class);
			in.putExtra("idCarona", idCarona);
			in.putExtra("idUsuario", idUsuario);
			in.putExtra("nome", name);
			in.putExtra("endereco_origem", origem);
			in.putExtra("endereco_destino", destino);
			in.putExtra("horario_origem", horarioOrigem);
			in.putExtra("horario_destino", horarioDestino);
			in.putExtra("tipo", tipo);
			in.putExtra("vagas_disponiveis", vagasDisponiveis);
			
			startActivity(in);
			return null;
			
		}
		
		protected void onPostExecute(Intent i){
			
		}
		
		
		
	}
	
	
	
	
	//requisição assincrona para listar as caronas
	public class ListaCaronas extends AsyncTask<Void, Void, ArrayList<HashMap<String, Object>>>{
		ProgressDialog aguardeCaronas = new ProgressDialog(CaronasActivity.this);
		
		@Override
		protected void onPreExecute() {
			
			aguardeCaronas.setTitle("Carregando caronas");
			aguardeCaronas.setMessage("Aguarde, por favor...");
			aguardeCaronas.setIndeterminate(true);
			aguardeCaronas.show();
			
		}

		@Override
		protected ArrayList<HashMap<String, Object>> doInBackground(Void... params) {
			String readJson = CustomHttpPost.readJson(Servidor.getServidor()
					+ "/buscaCaronas.php");
			try {
				JSONArray jsonArray = new JSONArray(readJson);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);

					String idUserAux = jsonObject.getString("id_usuario");
					
					//mostra so as caronas que nao sejam do usuario logado e ativas
					if ( 
//							(Integer.parseInt(idUserAux) != LoginActivity.getId_usuario())
//							&&
							(jsonObject.getString("ativo").equals("1"))
						)
					{
						String idCarona = jsonObject.getString("id_carona");
						String idUsuario = jsonObject.getString("id_usuario");
						String nome = jsonObject.getString("nome");
						String endOrigem = jsonObject.getString("endereco_origem");
						String endDestino = jsonObject
								.getString("endereco_destino");
						String horarioOrigem = jsonObject
								.getString("horario_origem");
						String horarioDestino = jsonObject
								.getString("horario_destino");
						String vagasDisponiveis = jsonObject
								.getString("vagas_disponiveis");
						
						String tipo = null;
						if (jsonObject.getString("tipo").equals("1")) {
							tipo = "Oferece";
						} else {
							tipo = "Pede";
						}

						HashMap<String, Object> map = new HashMap<String, Object>();

						map.put("id_carona", idCarona);
						map.put("id_usuario", idUsuario);
						map.put("nome", nome);
						map.put("tipo", tipo);
						map.put("endereco_origem", endOrigem);
						map.put("endereco_destino", endDestino);
						map.put("horario_origem", horarioOrigem);
						map.put("horario_destino", horarioDestino);
						map.put("vagas_disponiveis", vagasDisponiveis);
						

						if (jsonObject.getString("tipo").equals("1")) {
							map.put("imagem", R.drawable.oferece_carona);
						} else {
							map.put("imagem", R.drawable.pede_carona);
						}
					
						caronaList.add(map);

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return caronaList;
		}
		
		@Override
		protected void onPostExecute(ArrayList<HashMap<String, Object>> result){
			
			aguardeCaronas.dismiss();
			
			ListAdapter adapter = new SimpleAdapter(
				CaronasActivity.this,
				result,
				R.layout.list_item,
				new String[] 	{"nome", "endereco_origem","endereco_destino", "imagem", "vagas_disponiveis" },
				new int[] 		{R.id.TextNomeUsuario, R.id.textEndOrig, R.id.textEndDest, R.id.imageViewTipoCarona, R.id.TextVagasDisponiveis }
			);
			setListAdapter(adapter);
			
		}
		
		
		
	}
	
	
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	

}
