package com.example.preprodactionmessenger;

import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.MapTileIndex;

public class MyTitleSource extends TileSourceFactory {
    private static final String[] BASE_URL = new String[]{
            "https://mt0.google.com",
            "https://mt1.google.com",
            "https://mt2.google.com",
            "https://mt3.google.com",
    };
    private static final String AGENT_NAME = "Google-Roads";
    private static final int ZOOM_MIN_LEVEL = 0;
    private static final int ZOOM_MAX_LEVEL = 18;
    private static final int TILE_SIZE_PIXELS = 256;
    private static final String IMAGE_FILENAME_ENDING = ".png";
    private static final String URL_TYPE_MAP_LANGUAGE = "/vt/lyrs=m@169000000&scale=2&hl=en&x=";
    private static final String TILE_Y_LATITUDE = "&y=";
    private static final String TILE_Z_ZOOM = "&z=";

    //Google Map
    public static final OnlineTileSourceBase GoogleMaps = new XYTileSource(AGENT_NAME,
            ZOOM_MIN_LEVEL, ZOOM_MAX_LEVEL, TILE_SIZE_PIXELS, IMAGE_FILENAME_ENDING, BASE_URL) {
        @Override
        public String getTileURLString(long pMapTileIndex) {
            return getBaseUrl() + URL_TYPE_MAP_LANGUAGE + MapTileIndex.getX(pMapTileIndex) + TILE_Y_LATITUDE + MapTileIndex.getY(pMapTileIndex) + TILE_Z_ZOOM + MapTileIndex.getZoom(pMapTileIndex);
        }
    };
}