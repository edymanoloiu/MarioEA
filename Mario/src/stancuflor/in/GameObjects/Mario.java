package stancuflor.in.GameObjects;

import com.badlogic.gdx.math.Vector2;

public class Mario {

	private Vector2 position;

	private int width;
	private int height;

	private boolean sare;
	private float cat_sare;
	private int urca_coboara;

	private int nivel_drum = 0;

	private int directie = 1;

	public Mario(float x, float y, int width, int height) {

		this.setWidth(width);
		this.setHeight(height);

		this.setPosition(new Vector2(x, y));

		this.sare = false;
		this.cat_sare = 0;
		this.urca_coboara = 1;

	}

	public void update(float delta) {

		if (sare) {

			if (directie == -1)
				urca_coboara = directie;

			cat_sare += urca_coboara * 2;

			this.position.y += -urca_coboara * 2;

			if (cat_sare == 110.0f) {
				// inseamna ca am ajus la un maxim de inaltime, trebuie sa cobor

				urca_coboara = -1;

				if (nivel_drum < 1) {

					urca_coboara = 1;
					cat_sare = 0;
					sare = !sare;
					nivel_drum++;
					return;

				}

			}

			if (cat_sare == -110.0f) {
				// inseamna ca am ajus la un maxim de inaltime, trebuie sa cobor

				urca_coboara = 1;
				nivel_drum--;

				if (nivel_drum == 0) {

					urca_coboara = 1;
					cat_sare = 0;
					sare = !sare;
					return;

				}

			}

			if (cat_sare == 0) {
				// inseamna ca am ajuns pe solul care eram

				sare = !sare;
				urca_coboara = 1;

			}

		}

		System.out.println(urca_coboara + " " + cat_sare + " " + directie);

		// System.out.println(this.position);

	}

	public void onClick(int directie) {

		if ((directie == -1) && (nivel_drum == 0))
			return;

		if (!sare) {
			sare = !sare;
			this.directie = directie;
		}

	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

}
