package gui

import (
	"fyne.io/fyne/v2"
)

type CustomLayout struct {
}

func (d *CustomLayout) MinSize(objects []fyne.CanvasObject) fyne.Size {
	w, h := float32(0), float32(0)
	for _, o := range objects {
		childSize := o.Size()

		if w < childSize.Width {
			w = childSize.Width
		}

		padding := 8
		h += childSize.Height + float32(padding)
	}
	w += 20 // Padding
	h += 20 // Padding

	if w < 500 {
		w = 500
	}
	if h < 500 {
		h = 500
	}
	return fyne.NewSize(w, h)
}

func (d *CustomLayout) Layout(objects []fyne.CanvasObject, containerSize fyne.Size) {
	heightIndex := 0
	for _, o := range objects {

		pos := fyne.NewPos(0, float32(heightIndex))
		o.Move(pos)

		padding := 8
		heightIndex += int(o.Size().Height + float32(padding))
	}
}
