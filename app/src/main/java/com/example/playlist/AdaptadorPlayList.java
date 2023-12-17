package com.example.playlist;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

public class AdaptadorPlayList extends RecyclerView.Adapter<AdaptadorPlayList.ViewHolder> {

    MediaPlayer reproductor;
    Context context;
    List<Musica> dataSet;
    Runnable handlerTask = null;
    int idMusicaReproductor;

    public AdaptadorPlayList(@NonNull List<Musica> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public AdaptadorPlayList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_musica, parent, false);
        return new AdaptadorPlayList.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPlayList.ViewHolder holder, int position) {
        Musica musica = dataSet.get(position);

        holder.imgMusica.setImageResource(musica.getImage());
        holder.txtTitulo.setText(musica.getTitulo());
        holder.txtAutor.setText(musica.getAutor());

        holder.btnPlay.setOnClickListener(v -> playMusic(holder, musica));
        holder.btnPause.setOnClickListener(v -> pauseMusic(holder));
        holder.btnStop.setOnClickListener(v -> stopMusic(holder, musica));

        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int position, boolean changedByUser) {
                if (reproductor != null && changedByUser) {
                    reproductor.seekTo(position);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMusica, btnPlay, btnPause, btnStop;
        TextView txtTitulo, txtAutor;
        SeekBar seekBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMusica = itemView.findViewById(R.id.imgMusica);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtAutor = itemView.findViewById(R.id.txtAutor);
            btnPlay = itemView.findViewById(R.id.btnPlay);
            btnPause = itemView.findViewById(R.id.btnPause);
            btnStop = itemView.findViewById(R.id.btnStop);
            seekBar = itemView.findViewById(R.id.seekBar);
        }
    }

    private void playMusic(@NonNull ViewHolder holder, Musica musica) {
        if (reproductor != null) {
            if (reproductor.isPlaying() && musica.getId() != this.idMusicaReproductor) {
                reproductor.pause();
                handlerTask = null;
            }
        }
        this.idMusicaReproductor = musica.getId();
        reproductor = MediaPlayer.create(context, musica.getAudio());
        reproductor.seekTo(musica.getPosicionActual());
        reproductor.start();
        holder.seekBar.setMax(reproductor.getDuration());
        startTaskSetProgress(holder.seekBar, musica);
    }

    private void pauseMusic(@NonNull ViewHolder holder) {
        if (reproductor != null) {
            if (reproductor.isPlaying()) {
                reproductor.pause();
                handlerTask = null;
            }
        }
    }

    private void stopMusic(@NonNull ViewHolder holder, Musica musica) {
        try {
            reproductor.stop();
            reproductor.prepare();
            musica.setPosicionActual(0);
            holder.seekBar.setProgress(0);
            handlerTask = null;
            this.idMusicaReproductor = -1;
        } catch (IOException ignored) {
        }
    }

    private void startTaskSetProgress(SeekBar seekBar, Musica musica) {
        if (handlerTask == null) {
            handlerTask = () -> {
                seekBar.setProgress(reproductor.getCurrentPosition());
                musica.setPosicionActual(reproductor.getCurrentPosition());
                new Handler().postDelayed(handlerTask, 1000);
            };
            handlerTask.run();
        }
    }
}
