/*******************************************************************************
 * Copyright (c) 2012 Vincenzo Marzano.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Vincenzo Marzano - initial API and implementation
 ******************************************************************************/
package com.tecnojin.timekiller.games.descriptors.options;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Tutorial {

	private List<Page> pages;	

	public Tutorial(Page ... pages) {
		this.pages=new LinkedList<Page>();
		Collections.addAll(this.pages, pages);
	}

	public static class Page{
		private int title;
		private int text;
		private int image;

		public Page(int title,int image, int text){
			this.title=title;
			this.image=image;
			this.text=text;
	
		}

		public int getTitle() {
			return title;
		}

		public int getText() {
			return text;
		}

		public int getImage() {
			return image;
		}


	}

	public int getPageCount() {
		return pages.size();
	}
	public Page getPageAt(int index){
		return pages.get(index);
	}
}
